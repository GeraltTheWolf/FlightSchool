package hr.rezek.flightschool.controller;

import hr.rezek.flightschool.model.Course;
import hr.rezek.flightschool.model.UserData;
import hr.rezek.flightschool.repository.TopGunRepository;
import org.h2.engine.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/about")
public class AboutController {

    private Logger log = LoggerFactory.getLogger(Course.class);

    private final TopGunRepository repository;

    public AboutController(TopGunRepository topGunRepository) {
        this.repository = topGunRepository;
    }

    @GetMapping
    public String getCourses(Model model) {
        List<UserData> s = StreamSupport.stream(repository.getUserData().spliterator(),false).filter(userData -> userData.isInstructor()).collect(Collectors.toList());
        model.addAttribute("instructors", s);

        return "about";
    }
}
