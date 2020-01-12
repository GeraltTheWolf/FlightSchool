package hr.rezek.flightschool.apicontroller;

import hr.rezek.flightschool.model.BlogPost;
import hr.rezek.flightschool.repository.BlogPostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/blogpost", produces="application/json")
public class BlogPostController {

    private BlogPostRepository repository;

    public BlogPostController(BlogPostRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<BlogPost> findAll(){
       return repository.findAll();
    }
}
