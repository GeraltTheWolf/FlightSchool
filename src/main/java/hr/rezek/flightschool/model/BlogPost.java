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
    @JsonIgnore
    private Long id;

    private UUID userId;

    @NotEmpty(message = "Title is missing")
    @Size(min = 5, max = 50, message = "Title needs to be between 5 and 50 characters long")
    private String title;

    @NotEmpty(message = "Blog is missing")
    @Size(min = 20, message = "Content needs to be more than 20 characters long")
    private String content;

    private LocalDate lastEditedOn;

    private String author;

    private boolean enabled;
}
