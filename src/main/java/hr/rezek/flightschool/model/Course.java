package hr.rezek.flightschool.model;

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
    private String name;
    private String plane;
    private int duration;
    private UserData instructor;
}
