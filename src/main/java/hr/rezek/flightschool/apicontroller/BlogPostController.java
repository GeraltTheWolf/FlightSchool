package hr.rezek.flightschool.apicontroller;

import hr.rezek.flightschool.model.BlogPost;
import hr.rezek.flightschool.repository.BlogPostRepository;
import hr.rezek.flightschool.security.SecurityUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/blog", produces = "application/json")
@Secured("ROLE_USER")
public class BlogPostController {

    private BlogPostRepository blogPostRepository;

    public BlogPostController(BlogPostRepository repository) {
        this.blogPostRepository = repository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BlogPost> findOne(@PathVariable UUID userId) {
        Optional<BlogPost> blogPost = SecurityUtils.isAdmin() ?
                blogPostRepository.findByUserId(userId) :
                blogPostRepository.findByUserIdAndAuthorAndEnabled(userId, SecurityUtils.getUsername(), true);

        return blogPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<BlogPost> findAll() {
        return SecurityUtils.isAdmin() ?
                blogPostRepository.findAll() :
                blogPostRepository.findAllByAuthorAndEnabled(SecurityUtils.getUsername(), true);
    }

    @GetMapping(params = "date")
    public List<BlogPost> findAllLastEditedOnIsGreaterThanEqual(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return SecurityUtils.isAdmin() ?
                blogPostRepository.findAllByLastEditedOnIsGreaterThanEqual(date) :
                blogPostRepository.findAllByLastEditedOnIsGreaterThanEqualAndAuthorAndEnabled(date, SecurityUtils.getUsername(), true);
    }

    @GetMapping(params = "author")
    public ResponseEntity<List<BlogPost>> findAllByAuthor(String author) {
        if (SecurityUtils.isAdmin()) {
            return ResponseEntity.ok(blogPostRepository.findAllByAuthor(author));
        } else if (SecurityUtils.getUsername().equals(author)) {
            return ResponseEntity.ok(blogPostRepository.findAllByAuthorAndEnabled(author, true));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BlogPost> create(@Valid @RequestBody BlogPost blogPost)  {
            blogPost.setLastEditedOn(LocalDate.now());
            blogPost.setAuthor(SecurityUtils.getUsername());
            blogPost.setUserId(UUID.randomUUID());
            blogPost.setEnabled(true);
            return new ResponseEntity(blogPostRepository.save(blogPost),HttpStatus.CREATED);
    }

    @PutMapping(path = "/{userId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BlogPost> update(@PathVariable UUID userId, @Valid @RequestBody BlogPost updatedBlogPost) {
        Optional<BlogPost> blogPost = SecurityUtils.isAdmin() ?
                blogPostRepository.findByUserId(userId) :
                blogPostRepository.findByUserIdAndAuthorAndEnabled(userId, SecurityUtils.getUsername(), true);

        blogPost.ifPresent(bp -> {
            bp.setLastEditedOn(LocalDate.now());
            bp.setTitle(updatedBlogPost.getTitle());
            bp.setContent(updatedBlogPost.getContent());
            blogPostRepository.save(bp);
        });
        return blogPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{userId}", params = "enabled")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public void disableOrEnablePost(@PathVariable UUID userId, boolean enabled) {
        Optional<BlogPost> blogPost = SecurityUtils.isAdmin() ?
                blogPostRepository.findByUserId(userId) :
                blogPostRepository.findByUserIdAndAuthorAndEnabled(userId, SecurityUtils.getUsername(), true);

        blogPost.ifPresent(bp -> {
            blogPostRepository.setEnabledByUserId(userId, enabled);
        });
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void delete(@PathVariable UUID userId) {
        if (SecurityUtils.isAdmin() ? blogPostRepository.existsByUserId(userId) : blogPostRepository.existsByUserIdAndAuthor(userId, SecurityUtils.getUsername())) {
            blogPostRepository.removeByUserId(userId);
        }
    }
}
