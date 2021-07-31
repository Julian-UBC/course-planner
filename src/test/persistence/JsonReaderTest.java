package persistence;

import exceptions.EmptyList;
import exceptions.InvalidCredit;
import exceptions.InvalidName;
import model.Course;
import model.CourseList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Citation: Most of the code in this class are similar to JsonSerializationDemo (given in the project phase 2)
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CourseList cl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        } catch (Exception e) {
            fail("Unexpected exception (not IOException) is thrown");
        }
    }

    @Test
    void testReaderEmptyCourseList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCourseList.json");
        try {
            CourseList cl = reader.read();
            assertEquals("Dream", cl.getName());
            assertEquals(0, cl.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (Exception e) {
            fail("Unexpected exception (not IOException) is thrown");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCourseList.json");
        try {
            CourseList cl = reader.read();
            assertEquals("Dream", cl.getName());
            List<Course> courses = cl.getListOfCourses();
            assertEquals(3, courses.size());
            checkCourse("CPSC 221", 4, "planning", 0, courses.get(0));
            checkCourse("CPSC 210", 4, "ongoing", 0, courses.get(1));
            checkCourse("MATH 221", 3, "completed", 90, courses.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (Exception e) {
            fail("Unexpected exception (not IOException) is thrown");
        }
    }
}
