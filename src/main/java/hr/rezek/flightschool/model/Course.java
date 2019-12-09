package hr.rezek.flightschool.model;

import lombok.Getter;
import lombok.Setter;
import org.h2.engine.User;

import java.util.Date;

@Getter
@Setter
public class Course {
    private int id;
    private String name;
    private String plane;
    private int duration;
    private UserData instructor;
}
