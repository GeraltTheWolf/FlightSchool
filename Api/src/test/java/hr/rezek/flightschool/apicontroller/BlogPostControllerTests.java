package hr.rezek.flightschool.apicontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BlogPostControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findAllWithUserNoPosts() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/blog")
                                .with(user("test").password("testpass").roles("USER"))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*").isEmpty());
    }

    @Test
    void findAllWithAdminUser() throws Exception {
        this.mockMvc
                .perform(
                        get("/api/blog")
                                .with(user("test").password("testpass").roles("ADMIN"))
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$.*").isArray());
    }

    @ParameterizedTest(name = "Role {0} is supported.")
    @ValueSource(strings = { "ADMIN", "USER","INSTRUCTOR" })
    void create(String role) throws Exception {
        Map<String, String> blogPostMap = new HashMap<>();
        blogPostMap.put("title", "Test title");
        blogPostMap.put("content", "This is a valid and passing length of content");

        this.mockMvc
                .perform(
                        post("/api/blog")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(blogPostMap))
                                .with(csrf())
                                .with(user("admin").password("adminpass").roles(role))
                )
                .andExpect(status().isCreated());
    }

    @Test
    void createAnonymous() throws Exception {
        Map<String, String> blogPostMap = new HashMap<>();
        blogPostMap.put("title", "Test title");
        blogPostMap.put("content", "This is a valid and passing length of content");

        this.mockMvc
                .perform(
                        post("/api/blog")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(blogPostMap))
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createTitleMissing() throws Exception {
        Map<String, String> blogPostMap = new HashMap<>();
        blogPostMap.put("content", "This is a valid and passing length of content");

        this.mockMvc
                .perform(
                        post("/api/blog")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(blogPostMap))
                                .with(csrf())
                                .with(user("admin").password("adminpass").roles("ADMIN"))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void createTitleTooSmall() throws Exception {
        Map<String, String> blogPostMap = new HashMap<>();
        blogPostMap.put("title", "Test");
        blogPostMap.put("content", "This is a valid and passing length of content");

        this.mockMvc
                .perform(
                        post("/api/blog")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(blogPostMap))
                                .with(csrf())
                                .with(user("admin").password("adminpass").roles("ADMIN"))
                )
                .andExpect(status().isBadRequest());
    }


    @Test
    void createTitleTooBig() throws Exception {
        Map<String, String> blogPostMap = new HashMap<>();
        blogPostMap.put("title", "This title must fail becouse it is too long.This title must fail becouse it is too long.This title must fail becouse it is too long.");
        blogPostMap.put("content", "This is a valid and passing length of content");

        this.mockMvc
                .perform(
                        post("/api/blog")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(blogPostMap))
                                .with(csrf())
                                .with(user("admin").password("adminpass").roles("ADMIN"))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void createContentMissing() throws Exception {
        Map<String, String> blogPostMap = new HashMap<>();
        blogPostMap.put("title", "Test title");

        this.mockMvc
                .perform(
                        post("/api/blog")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(blogPostMap))
                                .with(csrf())
                                .with(user("admin").password("adminpass").roles("ADMIN"))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void createContentTooSmall() throws Exception {
        Map<String, String> blogPostMap = new HashMap<>();
        blogPostMap.put("title", "Test title");
        blogPostMap.put("content", "Too small content");

        this.mockMvc
                .perform(
                        post("/api/blog")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(blogPostMap))
                                .with(csrf())
                                .with(user("admin").password("adminpass").roles("ADMIN"))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteAdmin() throws Exception {
        this.mockMvc
                .perform(
                        delete("/api/blog/{userId}", UUID.fromString("1da792b5-9954-4f4d-bb1a-afd2ba049410"))
                                .with(user("admin").password("adminpass").roles("ADMIN"))
                )
                .andExpect(status().isNoContent());
    }

    @ParameterizedTest(name = "Role {0} is supported.")
    @ValueSource(strings = { "USER","INSTRUCTOR"})
    void deleteOtherRoles(String role) throws Exception {
        this.mockMvc
                .perform(
                        delete("/api/blog/{userId}", UUID.fromString("1da792b5-9954-4f4d-bb1a-afd2ba049410"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .with(user("zlatko").password("zlatkopass").roles(role))
                )
                .andExpect(status().isForbidden());
    }
}
