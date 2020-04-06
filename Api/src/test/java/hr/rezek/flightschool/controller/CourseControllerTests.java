package hr.rezek.flightschool.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CourseControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void invalidCourseSubmit() throws Exception {
        this.mockMvc
                .perform(
                        post("/course")
                                .param("plane", "F16")
                                .param("instructor", "Goose")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                                .with(user("Goose").password("goosepass").roles("USER", "INSTRUCTOR"))
                )
                .andExpect(status().isOk())
                .andExpect(view().name("course_new"));
    }

    @Test
    public void validCourseSubmit() throws Exception {
        this.mockMvc
                .perform(
                        post("/course")
                                .param("name", "Test")
                                .param("plane", "F16")
                                .param("duration", "15")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .with(csrf())
                                .with(user("Maverick").password("Passw0rd.123").roles("INSTRUCTOR"))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/course"));
    }
}
