package hr.rezek.flightschool.model;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int id;

    @NotEmpty(message = "{validation.course.name.empty}")
    @Size(min = 3, max = 25, message = "{validation.course.name.length}")
    private String name;
    private String plane;

    @NotNull(message = "{validation.course.duration.null}")
    @DecimalMin(value="1", message = "{validation.course.duration.min}")
    @DecimalMax(value = "50", message = "{validation.course.duration.max}")
    private int duration;
    private UserData instructor;
}
