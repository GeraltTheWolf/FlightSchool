package hr.rezek.flightschool.apicontroller;

import hr.rezek.flightschool.model.BlogPost;
import hr.rezek.flightschool.repository.BlogPostRepository;
import hr.rezek.flightschool.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/blog", produces = "application/json")
public class BlogController {

    private BlogPostRepository blogPostRepository;

    public BlogController(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    @GetMapping("/{blogPostId}")
    public ResponseEntity<BlogPost> findOne(@PathVariable long blogPostId) {
        Optional<BlogPost> blogPost = blogPostRepository.findById(blogPostId);
        return blogPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<BlogPost> findAll() {
        return blogPostRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@Valid @RequestBody BlogPost blogPost) {

        blogPost.setLastEditedOn(LocalDate.now());
        blogPost.setAuthor(SecurityUtils.getUsername());
        blogPost.setUserId(UUID.randomUUID());
        blogPost.setEnabled(true);

        return new ResponseEntity(blogPostRepository.save(blogPost), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{blogPostId}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BlogPost> update(@PathVariable long blogPostId, @Valid @RequestBody BlogPost updatedBlogPost) {
        Optional<BlogPost> blogPost = blogPostRepository.findById(blogPostId);
        blogPost.ifPresent(bp -> {
            bp.setLastEditedOn(LocalDate.now());
            bp.setTitle(updatedBlogPost.getTitle());
            bp.setContent(updatedBlogPost.getContent());
            blogPostRepository.save(bp);
        });
        return blogPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{blogPostId}")
    public ResponseEntity delete(@PathVariable long blogPostId) {
        if (blogPostRepository.existsById(blogPostId)) {
            blogPostRepository.deleteById(blogPostId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
