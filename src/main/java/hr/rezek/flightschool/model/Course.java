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

    @NotEmpty(message = "Name is empty")
    @Size(min = 3, max = 25, message = "Name has to be between 3 and 25 characters")
    private String name;
    private String plane;

    @NotNull(message = "Duration is missing")
    @DecimalMin(value="1", message = "Duration of course must be minimum 2 days")
    @DecimalMax(value = "50", message = "Duration of course can be maximum 50 days")
    private int duration;
    private UserData instructor;
}
