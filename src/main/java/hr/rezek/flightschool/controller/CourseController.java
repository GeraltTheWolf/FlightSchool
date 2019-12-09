package hr.rezek.flightschool.controller;

import hr.rezek.flightschool.model.Course;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @GetMapping
    public String getCourses(){
        Course course = new Course();
        return "course";
    }
}
