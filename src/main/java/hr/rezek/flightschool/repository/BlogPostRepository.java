package hr.rezek.flightschool.repository;

import hr.rezek.flightschool.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    Optional<BlogPost> findByUserId(UUID userId);

    Optional<BlogPost> findByUserIdAndAuthorAndEnabled(UUID userId, String author, boolean enabled);

    List<BlogPost> findAllByAuthor(String username);

    List<BlogPost> findAllByAuthorAndEnabled(String username, boolean enabled);

    List<BlogPost> findAllByLastEditedOnIsGreaterThanEqual(LocalDate date);

    List<BlogPost> findAllByLastEditedOnIsGreaterThanEqualAndAuthorAndEnabled(LocalDate date, String author,boolean enabled);

    @Transactional
    int removeByUserId(UUID userId);

    boolean existsByUserId(UUID userId);

    boolean existsByUserIdAndAuthor(UUID userId, String author);

    @Modifying
    @Query("update BlogPost set enabled = ?2 where userId = ?1")
    @Transactional
    int setEnabledByUserId(UUID userId, boolean enabled);
}
