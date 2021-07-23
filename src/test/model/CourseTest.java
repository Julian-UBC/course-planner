package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    Course course;

    @BeforeEach
    public void setUp() {
        course = new Course("CPSC 210", 4);
    }

    // Test the Course Constructor
    @Test
    public void testCourseConstructor() {
        assertEquals("CPSC 210", course.getCourseName());
        assertEquals(4, course.getCourseCredit());
        assertEquals("planning", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    @Test
    // Test the changeCourseName method
    public void testChangeCourseName() {
        course.changeCourseName("CPSC 213");

        assertEquals("CPSC 213", course.getCourseName());
        assertEquals(4, course.getCourseCredit());
        assertEquals("planning", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    @Test
    // Test the changeCourseCredit method
    public void testChangeCourseCredit() {
        course.changeCourseCredit(3);

        assertEquals("CPSC 210", course.getCourseName());
        assertEquals(3, course.getCourseCredit());
        assertEquals("planning", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    @Test
    // Test the setOngoing method
    public void testSetOngoing() {
        course.setOngoing();

        assertEquals("CPSC 210", course.getCourseName());
        assertEquals(4, course.getCourseCredit());
        assertEquals("ongoing", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    @Test
    // Test the setCompleted method
    public void testSetCompleted() {
        course.setCompleted();

        assertEquals("CPSC 210", course.getCourseName());
        assertEquals(4, course.getCourseCredit());
        assertEquals("completed", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    @Test
    // Test the setPlanning method
    public void testSetPlanning() {
        course.setCompleted();
        assertEquals("completed", course.getCourseStatus());

        course.setPlanning();
        assertEquals("CPSC 210", course.getCourseName());
        assertEquals(4, course.getCourseCredit());
        assertEquals("planning", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    @Test
    // Test the setGrade method
    public void testSetGrade() {
        course.setGrade(95);

        assertEquals("CPSC 210", course.getCourseName());
        assertEquals(4, course.getCourseCredit());
        assertEquals("completed", course.getCourseStatus());
        assertEquals(95, course.getCourseGrade());
    }
}