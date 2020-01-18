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

    @NotEmpty(message = "{msg.errors.name.empty}")
    @Size(min = 3, max = 25, message = "{msg.errors.name.length}")
    private String name;
    private String plane;

    @NotNull(message = "{msg.errors.duration.null}")
    @DecimalMin(value="1", message = "{msg.errors.duration.min}")
    @DecimalMax(value = "50", message = "{msg.errors.duration.max}")
    private int duration;
    private UserData instructor;
}
