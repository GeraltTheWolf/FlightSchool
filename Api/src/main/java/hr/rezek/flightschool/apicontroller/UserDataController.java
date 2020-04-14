package hr.rezek.flightschool.apicontroller;

import hr.rezek.flightschool.model.BlogPost;
import hr.rezek.flightschool.model.UserData;
import hr.rezek.flightschool.repository.UserDataRepository;
import hr.rezek.flightschool.security.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/user", produces = "application/json")
public class UserDataController {

    private UserDataRepository userDataRepository;

    public UserDataController(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @GetMapping
    public List<String> getUserNames() {

        List<String> users = this.userDataRepository.findAll().stream().map(UserData::getUsername).collect(Collectors.toList());

        return users;
    }
}
