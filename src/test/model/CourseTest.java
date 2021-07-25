package model;

import exceptions.InvalidCredit;
import exceptions.InvalidName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    Course course;

    @BeforeEach
    public void setUp() {
        try {
            course = new Course("CPSC 210", 4);
        }
        catch (InvalidName invalidName) {
            fail("Unexpected InvalidName is thrown");
        }
        catch (InvalidCredit invalidCredit) {
            fail("Unexpected InvalidCredit is thrown");
        }
    }

    // Test the Course Constructor
    @Test
    // No exception is thrown
    public void testCourseConstructorNoException() {
        try {
            course = new Course("MATH 221", 3);
            // do nothing
        } catch (InvalidName invalidName) {
            fail("Unexpected InvalidName is thrown");
        } catch (InvalidCredit invalidCredit) {
            fail("Unexpected InvalidCredit is thrown");
        }

        assertEquals("MATH 221", course.getCourseName());
        assertEquals(3, course.getCourseCredit());
        assertEquals("planning", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    @Test
    // InvalidName exception is thrown
    public void testCourseConstructorInvalidNameException() {
        try {
            course = new Course("", 3);
            fail("InvalidName not thrown");
        } catch (InvalidName invalidName) {
            // do nothing
        } catch (InvalidCredit invalidCredit) {
            fail("Unexpected InvalidCredit is thrown");
        }

        assertEquals("CPSC 210", course.getCourseName());
        assertEquals(4, course.getCourseCredit());
        assertEquals("planning", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    @Test
    // InvalidCredit exception is thrown
    public void testCourseConstructorInvalidCreditException() {
        try {
            course = new Course("CPSC 210", 0);
            fail("InvalidCredit not thrown");
        } catch (InvalidName invalidName) {
            fail("Unexpected InvalidName is thrown");
        } catch (InvalidCredit invalidCredit) {
            // do nothing
        }

        assertEquals("CPSC 210", course.getCourseName());
        assertEquals(4, course.getCourseCredit());
        assertEquals("planning", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    // Test the changeCourseName method
    @Test
    // No exception thrown
    public void testChangeCourseNameNoException() {
        try {
            course.changeCourseName("CPSC 213");
            // do nothing
        } catch (InvalidName invalidName) {
            fail("Unexpected InvalidName is thrown");
        }

        assertEquals("CPSC 213", course.getCourseName());
        assertEquals(4, course.getCourseCredit());
        assertEquals("planning", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    @Test
    // InvalidName thrown
    public void testChangeCourseNameInvalidNameException() {
        try {
            course.changeCourseName("");
            fail("InvalidName not thrown");
        } catch (InvalidName invalidName) {
            // do nothing
        }

        assertEquals("CPSC 210", course.getCourseName());
        assertEquals(4, course.getCourseCredit());
        assertEquals("planning", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    // Test the changeCourseCredit method
    @Test
    // No exception thrown
    public void testChangeCourseCreditNoException() {
        try {
            course.changeCourseCredit(3);
        } catch (InvalidCredit invalidCredit) {
            fail("Unexpected InvalidCredit is thrown");
        }

        assertEquals("CPSC 210", course.getCourseName());
        assertEquals(3, course.getCourseCredit());
        assertEquals("planning", course.getCourseStatus());
        assertEquals(0, course.getCourseGrade());
    }

    @Test
    // InvalidCredit thrown
    public void testChangeCourseCreditInvalidCreditException() {
        try {
            course.changeCourseCredit(0);
            fail("InvalidCredit not thrown");
        } catch (InvalidCredit invalidCredit) {
            // do nothing
        }

        assertEquals("CPSC 210", course.getCourseName());
        assertEquals(4, course.getCourseCredit());
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