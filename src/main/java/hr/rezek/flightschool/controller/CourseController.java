package hr.rezek.flightschool.controller;

import hr.rezek.flightschool.model.Course;
import hr.rezek.flightschool.model.UserData;
import hr.rezek.flightschool.repository.TopGunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;

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
        model.addAttribute("newCourse", new Course());
        model.addAttribute("courses", repository.getCourses());
        return "course";
    }

    @PostMapping
    public String createNewExercise(@Valid Course course, Errors errors, Model model){
        if(errors.hasErrors()){
            log.info("There were errors in the submitted form");
            return "course";
        }

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
             username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        UserData instructor = repository.getUserDataByUserName(username);
        course.setInstructor(instructor);
        repository.saveCourse(course);
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
