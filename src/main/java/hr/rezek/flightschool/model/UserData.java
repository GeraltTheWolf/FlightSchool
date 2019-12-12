package hr.rezek.flightschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    int id;
    private String firstName;
    private String lastName;
    private String username;
    private boolean isInstructor;
    private String biography;
}
