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
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            CourseList cl = new CourseList("Dream");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        } catch (Exception e) {
            fail("Unexpected exception (not IOException) is thrown");
        }
    }

    @Test
    void testWriterEmptyCourseList() {
        try {
            CourseList cl = new CourseList("Dream");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCourseList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCourseList.json");
            cl = reader.read();
            assertEquals("Dream", cl.getName());
            assertEquals(0, cl.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (Exception e) {
            fail("Unexpected exception (not IOException) is thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            CourseList cl = new CourseList("Dream Worklist");
            cl.addCourse(new Course("CPSC 110", 4));
            Course course = new Course("MATH 100", 3);
            course.setGrade(95);
            cl.addCourse(course);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCourseList.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCourseList.json");
            cl = reader.read();
            assertEquals("Dream Worklist", cl.getName());
            List<Course> courses = cl.getListOfCourses();
            assertEquals(2, courses.size());
            checkCourse("CPSC 110", 4, "planning", 0, courses.get(0));
            checkCourse("MATH 100", 3, "completed", 95, courses.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (Exception e) {
            fail("Unexpected exception (not IOException) is thrown");
        }
    }
}
