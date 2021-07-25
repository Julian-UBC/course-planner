package model;

import exceptions.InvalidCredit;
import exceptions.InvalidName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseListTest {

    private CourseList courseList;
    private Course course;
    private Course course2;

    private boolean isSuccessful;

    @BeforeEach
    public void setUp() {
        try {
            courseList = new CourseList("Dream Worklist");
        } catch (InvalidName invalidName) {
            fail("Unexpected InvalidName is thrown");
        }

        try {
            course = new Course("CPSC 210", 4);
        } catch (InvalidName invalidName) {
            fail("Unexpected InvalidName is thrown");
        } catch (InvalidCredit invalidCredit) {
            fail("Unexpected InvalidCredit is thrown");
        }

        try {
            course2 = new Course("MATH 221", 3);
        } catch (InvalidName invalidName) {
            fail("Unexpected InvalidName is thrown");
        } catch (InvalidCredit invalidCredit) {
            fail("Unexpected InvalidCredit is thrown");
        }
    }

    // Test the constructor
    @Test
    // No exception thrown
    public void testCourseListConstructorNoException() {
        try {
            courseList = new CourseList("Backup Worklist");
            // do nothing
        } catch (InvalidName invalidName) {
            fail("Unexpected InvalidName is thrown");
        }

        assertEquals("Backup Worklist", courseList.getName());
        assertTrue(courseList.isEmpty());
    }

    @Test
    // InvalidName thrown
    public void testCourseListConstructorInvalidNameException() {
        try {
            courseList = new CourseList("");
            fail("InvalidName not thrown");
        } catch (InvalidName invalidName) {
            // do nothing
        }

        assertEquals("Dream Worklist", courseList.getName());
        assertTrue(courseList.isEmpty());
    }

    // Test addCourse method
    @Test
    // Adding course when the course is not in the list
    public void testAddCourseNotInList() {
        assertFalse(courseList.isCourseInList("CPSC 210"));

        isSuccessful = courseList.addCourse(course);

        assertTrue(isSuccessful);
        assertEquals(1, courseList.length());
        assertTrue(courseList.isCourseInList("CPSC 210"));
    }

    @Test
    // Adding course when the course is in the list
    public void testAddCourseInList() {
        assertTrue(courseList.addCourse(course));

        isSuccessful = courseList.addCourse(course);

        assertFalse(isSuccessful);
        assertEquals(1, courseList.length());
    }

    @Test
    // Adding multiple courses to the list
    public void testAddCourseMultiple() {
        // ADD 1
        isSuccessful = courseList.addCourse(course);

        assertTrue(isSuccessful);
        assertEquals(1, courseList.length());
        assertTrue(courseList.isCourseInList("CPSC 210"));

        // ADD ANOTHER ONE
        isSuccessful = courseList.addCourse(course2);

        assertTrue(isSuccessful);
        assertEquals(2, courseList.length());
        assertTrue(courseList.isCourseInList("MATH 221"));

        // FAIL TO ADD
        isSuccessful = courseList.addCourse(course2);

        assertFalse(isSuccessful);
        assertEquals(2, courseList.length());
    }

    // Test removeRecentCourse method
    @Test
    // When list is empty
    public void testRemoveRecentCourseEmpty() {
        assertTrue(courseList.isEmpty());

        isSuccessful = courseList.removeRecentCourse();

        assertFalse(isSuccessful);
        assertEquals(0, courseList.length());
    }

    @Test
    // When list is not empty
    public void testRemoveRecentCourseNotEmpty() {
        assertTrue(courseList.addCourse(course));
        assertTrue(courseList.addCourse(course2));

        // REMOVE MATH 221
        isSuccessful = courseList.removeRecentCourse();

        assertTrue(isSuccessful);
        assertEquals(1, courseList.length());
        assertTrue(courseList.isCourseInList("CPSC 210"));
        assertFalse(courseList.isCourseInList("MATH 221"));

        // REMOVE CPSC 210
        isSuccessful = courseList.removeRecentCourse();

        assertTrue(isSuccessful);
        assertTrue(courseList.isEmpty());
        assertFalse(courseList.isCourseInList("CPSC 210"));
    }

    // Test removeCourse method
    @Test
    // When course is not in the list
    public void testRemoveCourseNotInList() {
        assertFalse(courseList.isCourseInList("CPSC 210"));
        isSuccessful = courseList.removeCourse("CPSC 210");

        assertFalse(isSuccessful);

        assertTrue(courseList.addCourse(course));

        assertFalse(courseList.isCourseInList("MATH 221"));
        isSuccessful = courseList.removeCourse("MATH 221");

        assertFalse(isSuccessful);
    }

    @Test
    // When course is in the list
    public void testRemoveCourseInList() {
        assertTrue(courseList.addCourse(course));
        assertTrue(courseList.addCourse(course2));
        assertTrue(courseList.isCourseInList("CPSC 210"));
        assertTrue(courseList.isCourseInList("MATH 221"));

        isSuccessful = courseList.removeCourse("CPSC 210");

        assertTrue(isSuccessful);
        assertFalse(courseList.isCourseInList("CPSC 210"));
        assertTrue(courseList.isCourseInList("MATH 221"));
        assertEquals(1, courseList.length());

        isSuccessful = courseList.removeCourse("MATH 221");

        assertTrue(isSuccessful);
        assertFalse(courseList.isCourseInList("MATH 221"));
        assertTrue(courseList.isEmpty());
    }

    // Test setCourseListName method
    @Test
    // No exception thrown
    public void testSetCourseListNameNoException() {
        assertEquals("Dream Worklist", courseList.getName());

        try {
            courseList.setCourseListName("Backup Worklist");
        } catch (InvalidName invalidName) {
            fail("Unexpected InvalidName is thrown");
        }

        assertEquals("Backup Worklist", courseList.getName());
    }

    @Test
    // InvalidName thrown
    public void testSetCourseListNameInvalidNameException() {
        assertEquals("Dream Worklist", courseList.getName());

        try {
            courseList.setCourseListName("");
            fail("InvalidName not thrown");
        } catch (InvalidName invalidName) {
            //do nothing
        }

        assertEquals("Dream Worklist", courseList.getName());
    }

    // Test getCourse method
    @Test
    // when course is in the list
    public void testGetCourseInList() {
        assertTrue(courseList.addCourse(course));
        assertTrue(courseList.addCourse(course2));

        Course course101 = courseList.getCourse("CPSC 210");
        Course course102 = courseList.getCourse("MATH 221");

        assertEquals(course, course101);
        assertEquals(course2, course102);
        assertEquals("CPSC 210", course101.getCourseName());
        assertEquals("MATH 221", course102.getCourseName());
    }

    @Test
    // When course is not in the list
    public void testGetCourseNotInList() {
        assertTrue(courseList.isEmpty());
        Course course103 = courseList.getCourse("CPSC 210");

        assertEquals(null, course103);

        assertTrue(courseList.addCourse(course));
        Course course104 = courseList.getCourse("MATH 221");

        assertEquals(null, course104);
    }

}
















