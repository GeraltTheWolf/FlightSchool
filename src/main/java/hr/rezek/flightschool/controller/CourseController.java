package hr.rezek.flightschool.controller;

import hr.rezek.flightschool.model.Course;
import hr.rezek.flightschool.repository.TopGunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
@RequestMapping("/course")
public class CourseController {

    private Logger log = LoggerFactory.getLogger(Course.class);

    private final TopGunRepository repository;

    public CourseController(TopGunRepository topGunRepository) {
        this.repository = topGunRepository;
    }

    @GetMapping
    public String getCourses(Model model) {
        model.addAttribute("courses", repository.getCourses());
        return "course";
    }

//    @GetMapping
//    @RequestMapping("/instructor/{instructorId}")
//    public Object getAllInstructorCourses(@PathVariable("instructorId") int instructorId) {
//        return repository.getCoursesByInstructorId(instructorId);
//    }
//
//    @GetMapping
//    @RequestMapping("/aa")
//    public Object getAllUserData() {
//        return repository.getUserData();
//    }
//
//    @GetMapping
//    @RequestMapping("/bb")
//    public Object getAllUserCourses() {
//        return repository.getUserCourses();
//    }
}
