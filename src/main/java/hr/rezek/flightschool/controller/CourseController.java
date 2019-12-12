package hr.rezek.flightschool.controller;

import hr.rezek.flightschool.model.Course;
import hr.rezek.flightschool.model.UserCourse;
import hr.rezek.flightschool.model.UserData;
import hr.rezek.flightschool.repository.TopGunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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

    @GetMapping
    @RequestMapping("/new")
    public String getCourseNew(Model model, Authentication authentication) {
        var roles = authentication.getAuthorities().toString();
        if (roles.contains("ROLE_INSTRUCTOR")) {
            model.addAttribute("newCourse", new Course());
            return "course_new";
        } else {
            return "redirect:/course";
        }
    }

    @GetMapping
    @RequestMapping("/user")
    public String getCourseUser(Model model, Principal principal) {
        model.addAttribute("newCourse", new Course());
        UserData user = repository.getUserDataByUserName(principal.getName());
        model.addAttribute("userCourses", repository.getUserCoursesByUserDataId(user.getId()));

        return "course_user";
    }

    @GetMapping
    @RequestMapping("/user/{userCourseId}")
    public String attendCourse(@PathVariable("userCourseId") int userCourseId) {
        UserCourse userCourse = repository.getUserCourseById(userCourseId);
        userCourse.setNumberOfAttendances(userCourse.getNumberOfAttendances() + 1);
        repository.UpdateUserCourse(userCourse);

        return "redirect:/course/user";
    }


    @GetMapping
    @RequestMapping("/instructor")
    public String getCourseInstructor(Model model, Principal principal) {
        UserData user = repository.getUserDataByUserName(principal.getName());
        model.addAttribute("Courses", repository.getCoursesByInstructorId(user.getId()));

        return "course_instructor";
    }

    @PostMapping
    public String createNewExercise(@Valid Course course, Errors errors, Model model, Principal principal) {
        if (errors.hasErrors()) {
            log.info("There were errors in the submitted form");
            return "course_new";
        }
        
        UserData instructor = repository.getUserDataByUserName(principal.getName());
        course.setInstructor(instructor);
        repository.saveCourse(course);

        return "redirect:/course";
    }

}
