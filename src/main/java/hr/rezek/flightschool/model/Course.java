package hr.rezek.flightschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private int id;

    @NotEmpty(message = "Name is empty")
    private String name;
    private String plane;

    @NotNull(message = "Duration is missing")
    @DecimalMin(value="1", message = "Duration of course must be minimum 2 days")
    @DecimalMax(value = "50", message = "Duration of course can be maximum 50 days")
    private int duration;
    private UserData instructor;
}
