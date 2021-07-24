package model;

public class CourseList {

    private String courseListName;

    // REQUIRES: name to be a non-empty string
    // EFFECTS: initializes a CourseList with given name & empty course in the list
    public CourseList(String name) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: if course is not in the list, add course to the list and return true
    //          if course is in the list, return false
    public boolean addCourse(Course course) {
        return false; //stub
    }

    // MODIFIES: this
    // EFFECTS: if the list is not empty, remove the last added course in the list and return true
    //          if the list is empty, return false
    public boolean removeRecentCourse() {
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if course is in the list, remove course from the list and return true
    //          if course is not in the list, return false
    public boolean removeCourse(String courseName) {
        return false;
    }

    // MODIFIES: this
    // EFFECTS: change the CourseList name to the given name
    public void setCourseListName(String name) {
        //stub
    }

    // EFFECTS: return the length of the list
    public int length() {
        return 0;
    }

    // REQUIRES: course is in the list
    // EFFECTS: return the requested course
    public Course getCourse(String name) {
        return new Course("ABCD 012", 1); //stub
    }

    // EFFECTS: return true if given course's name is in the list; otherwise false
    public boolean isCourseInList(String name) {
        return false; //stub
    }

    // EFFECTS: return CourseList's name
    public String getName() {
        return ""; //stub
    }

    // EFFECTS: return true if the list is empty; otherwise false
    public boolean isEmpty() {
        return false; //stub
    }
}
