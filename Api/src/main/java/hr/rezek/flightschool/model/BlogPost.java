package hr.rezek.flightschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID userId;

    @NotEmpty(message = "{validation.blogPost.title.empty}")
    @Size(min = 5, max = 50, message = "{validation.blogPost.title.size}")
    private String title;

    @NotEmpty(message = "{validation.blogPost.content.empty}")
    @Size(min = 20, message = "{validation.blogPost.content.size}")
    private String content;

    private LocalDate lastEditedOn;

    private String author;

    private boolean enabled;
}
