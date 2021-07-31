package persistence;

import exceptions.InvalidCredit;
import exceptions.InvalidName;
import model.Course;
import model.CourseList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads CourseList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CourseList read() throws IOException, InvalidName, InvalidCredit {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCourseList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses CourseList from JSON object and returns it
    private CourseList parseCourseList(JSONObject jsonObject) throws InvalidName, InvalidCredit {
        String name = jsonObject.getString("name");
        CourseList cl = null;
        cl = new CourseList(name);
        addWorklist(cl, jsonObject);
        return cl;
    }

    // MODIFIES: wr
    // EFFECTS: parses worklist from JSON object and adds them to CourseList
    private void addWorklist(CourseList cl, JSONObject jsonObject) throws InvalidName, InvalidCredit {
        JSONArray jsonArray = jsonObject.getJSONArray("worklist");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(cl, nextCourse);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses course from JSON object and adds it to CourseList
    private void addCourse(CourseList cl, JSONObject jsonObject) throws InvalidName, InvalidCredit {
        String name = jsonObject.getString("name");
        int credit = Integer.parseInt(jsonObject.getString("credit"));
        String status = jsonObject.getString("status");
        int grade = Integer.parseInt(jsonObject.getString("grade"));
        Course course = null;
        course = new Course(name, credit);
        course = setCourseStatus(status, course);
        course = setCourseGrade(grade, course);
        cl.addCourse(course);
    }

    // EFFECTS: change the given course status and return the course
    private Course setCourseStatus(String status, Course course) {
        if (status.equals("planning")) {
            course.setPlanning();
        } else if (status.equals("ongoing")) {
            course.setOngoing();
        } else {
            course.setCompleted();
        }

        return course;
    }

    // EFFECTS: change the given course grade and return the course
    private Course setCourseGrade(int grade, Course course) {
        if (grade == 0) {
            // do nothing
        } else {
            course.setGrade(grade);
        }

        return course;
    }
}
