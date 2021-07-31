package persistence;

import model.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCourse(String name, int credit, String status, int grade, Course course) {
        assertEquals(name, course.getCourseName());
        assertEquals(credit, course.getCourseCredit());
        assertEquals(grade, course.getCourseGrade());
        assertEquals(status, course.getCourseStatus());
    }
}
