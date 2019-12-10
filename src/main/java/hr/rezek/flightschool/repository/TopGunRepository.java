package hr.rezek.flightschool.repository;

import hr.rezek.flightschool.model.Course;
import hr.rezek.flightschool.model.UserCourse;
import hr.rezek.flightschool.model.UserData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TopGunRepository {
    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert courseInserter;
    private final SimpleJdbcInsert userCourseInserter;

    public TopGunRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.courseInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Course")
                .usingGeneratedKeyColumns("id");

        this.userCourseInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("UserCourse")
                .usingGeneratedKeyColumns("id");
    }

    //region UserData
    public Iterable<UserData> getUserData() {
        return jdbc.query("select id, firstName, lastName, username from UserData", this::mapRowToUserData);
    }

    public UserData getUserDataByUserName(String username) {
        return jdbc.queryForObject("select id, firstName, lastName, username from UserData where username = ?", this::mapRowToUserData, username);
    }

    public UserData getUserDataById(int id) {
        return jdbc.queryForObject("select id, firstName, lastName, username from UserData where id = ?", this::mapRowToUserData, id);
    }

    private UserData mapRowToUserData(ResultSet rs, int rowNum) throws SQLException {
        UserData userData = new UserData();
        userData.setId(rs.getInt("id"));
        userData.setFirstName(rs.getString("firstName"));
        userData.setLastName(rs.getString("lastName"));
        userData.setUsername(rs.getString("username"));
        return userData;
    }
    //endregion

    //region Courses
    public Iterable<Course> getCourses() {
        return jdbc.query("select id, name, userDataId, duration, plane from Course", this::mapRowToCourse);
    }

    public Course getCourseById(int id) {
        return jdbc.queryForObject("select id, name, userDataId, duration, plane from Course  where id = ?", this::mapRowToCourse, id);
    }

    public Iterable<Course> getCoursesByInstructorId(int instructorId) {
        return jdbc.query("select id, name, userDataId, duration, plane from Course where userDataId = ?", this::mapRowToCourse, instructorId);
    }

    public Course saveCourse(Course course) {
        course.setId(_saveCourse(course));
        return course;
    }

    private int _saveCourse(Course course) {
        Map<String, Object> values = new HashMap<>();

        values.put("name", course.getName());
        values.put("userDataId", course.getInstructor().getId());
        values.put("duration", course.getDuration());
        values.put("plane", course.getPlane());

        return courseInserter.executeAndReturnKey(values).intValue();
    }

    private Course mapRowToCourse(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setName(rs.getString("name"));
        course.setDuration(rs.getInt("duration"));
        course.setPlane(rs.getString("plane"));
        course.setInstructor(getUserDataById(rs.getInt("userDataId")));
        return course;
    }
    //endregion

    //region UserCourses
    public Iterable<UserCourse> getUserCourses() {
        return jdbc.query("select id, courseId, userDataId, dateStarted, numberOfAttendances from UserCourse", this::mapRowToUserCourse);
    }

    public UserCourse getUserCourseById(int id) {
        return jdbc.queryForObject("select id, courseId, userDataId, dateStarted, numberOfAttendances from UserCourse where id = ?", this::mapRowToUserCourse, id);
    }

    public UserCourse getUserCoursesByUserDataId(int userDataId) {
        return jdbc.queryForObject("select id, courseId, userDataId, dateStarted, numberOfAttendances from UserCourse where userDataId = ?", this::mapRowToUserCourse, userDataId);
    }

    public UserCourse saveUserCourse(UserCourse userCourse) {
        userCourse.setId(_saveUserCourse(userCourse));
        return userCourse;
    }

    private int _saveUserCourse(UserCourse userCourse) {
        Map<String, Object> values = new HashMap<>();

        values.put("courseId", userCourse.getCourse().getId());
        values.put("userDataId", userCourse.getUserData().getId());
        values.put("dateStarted", userCourse.getDateStarted());
        values.put("numberOfAttendances", userCourse.getNumberOfAttendances());

        return userCourseInserter.executeAndReturnKey(values).intValue();
    }

    private UserCourse mapRowToUserCourse(ResultSet rs, int rowNum) throws SQLException {
        UserCourse userCourse = new UserCourse();
        userCourse.setId(rs.getInt("id"));
        userCourse.setDateStarted(rs.getDate("dateStarted").toLocalDate());
        userCourse.setNumberOfAttendances(rs.getInt("numberOfAttendances"));
        userCourse.setCourse(getCourseById(rs.getInt("courseId")));
        userCourse.setUserData(getUserDataById(rs.getInt("userDataId")));
        return userCourse;
    }
    //endregion
}
