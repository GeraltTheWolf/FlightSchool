package data;

import model.UserManager;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RestUserManager implements UserManager {

    @Override
    public List<String> GetAll() {

        try {
//            List<String> authors = new ArrayList<>();
//            authors.add("Bero");
//            authors.add("Perica");
//            authors.add("Janko");
//            return authors;
            return Arrays.asList(Objects.requireNonNull(new RestTemplate().getForEntity("http://localhost:8080/api/user", String[].class).getBody()));
        } catch (Exception ex) {
            //TODO Logging of exception
            return new ArrayList<>();
        }
    }
}
