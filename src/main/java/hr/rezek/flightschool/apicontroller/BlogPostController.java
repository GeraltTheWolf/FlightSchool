package hr.rezek.flightschool.apicontroller;

import hr.rezek.flightschool.model.BlogPost;
import hr.rezek.flightschool.repository.BlogPostRepository;
import hr.rezek.flightschool.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/blogpost", produces = "application/json")
@Secured("ROLE_USER")
public class BlogPostController {

    private BlogPostRepository blogPostRepository;

    public BlogPostController(BlogPostRepository repository) {
        this.blogPostRepository = repository;
    }

    @GetMapping
    public List<BlogPost> findAll() {
        return SecurityUtils.isAdmin() ? blogPostRepository.findAll() : blogPostRepository.findAllByAuthorAndEnabled(SecurityUtils.getUsername(), true);
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

    @GetMapping("/{userId}")
    public ResponseEntity<BlogPost> findOne(@PathVariable UUID userId) {
        Optional<BlogPost> blogPost = Optional.ofNullable(SecurityUtils.isAdmin() ? blogPostRepository.findByUserId(userId) : blogPostRepository.findByAuthorAndUserIdAndEnabled(SecurityUtils.getUsername(), userId, true));
        return blogPost.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
