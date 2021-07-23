package model;

public class Course {

    private String courseName;
    private int courseCredit;

    private int grade;
    // Status: planning, ongoing, completed
    private String status;

    // REQUIRES: name not an empty string, credit > 0
    // EFFECTS: initializes a course with given name and credit, planning as status, and grade = 0
    public Course(String name, int credit) {
        //stub
    }

    // REQUIRES: name not an empty string
    // MODIFIES: this
    // EFFECTS: change the course's name to the given name
    public void changeCourseName(String name) {
        //stub
    }

    // REQUIRES: credit > 0
    // MODIFIES: this
    // EFFECTS: change the course's credit to the given credit
    public void changeCourseCredit(int credit) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: set status to ongoing
    public void setOngoing() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: set status to completed
    public void setCompleted() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: set status to planning
    public void setPlanning() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: set my course's grade to the given grade & set the course's status to completed
    public int setGrade(int grade) {
        return 0; //stub
    }

    // EFFECTS: return the course's name
    public String getCourseName() {
        return ""; //stub
    }

    // EFFECTS: return the course's credit
    public int getCourseCredit() {
        return 0; //stub
    }

    // EFFECTS: return the status of the course
    public String getCourseStatus() {
        return ""; //stub
    }

    // EFFECTS: return the grade gotten in the course
    public int getCourseGrade() {
        return 0; //stub
    }
}
