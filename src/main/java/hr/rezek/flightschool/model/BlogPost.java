package hr.rezek.flightschool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID guidId;

    @NotEmpty(message = "Title is missing")
    @Size(min = 5, max = 50, message = "Title needs to be between 5 and 50 characters long")
    private String title;

    @NotEmpty(message = "Blog is missing")
    @Size(min = 20, message = "Blog needs to be more than 20 characters long")
    private String post;

    private LocalDate lastEditedOn;

    @NotEmpty(message = "Author is missing")
    @Size(min = 5, max = 50, message = "Author needs to be between 5 and 50 characters long")
    private String author;
}
