package hr.rezek.flightschool.repository;

import hr.rezek.flightschool.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    BlogPost findByUserId(UUID userId);

    BlogPost findByAuthorAndUserIdAndEnabled(String author, UUID userId, boolean enabled);

    List<BlogPost> findAllByAuthor(String username);

    List<BlogPost> findAllByAuthorAndEnabled(String username, boolean enabled);

    List<BlogPost> findAllByAuthorAndUserIdAndEnabled(String username, UUID userId, boolean enabled);

    List<BlogPost> findAllByAuthorAndLastEditedOnIsGreaterThanEqual(String author, LocalDate date);
}
