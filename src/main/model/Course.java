package model;

import exceptions.InvalidCredit;
import exceptions.InvalidName;

public class Course {

    private String courseName;
    private int courseCredit;

    private int grade;
    // Status: planning, ongoing, completed
    private String status;

    private static final String PLANNING = "planning";
    private static final String ONGOING = "ongoing";
    private static final String COMPLETED = "completed";

    // EFFECTS: initializes a course with given name and credit, planning as status, and grade = 0
    //          throw InvalidName exception if name is an empty string
    //          throw InvalidCredit exception if credit is <= 0
    public Course(String name, int credit) throws InvalidName, InvalidCredit {
        if (name.length() == 0) {
            throw new InvalidName();
        } else if (credit <= 0) {
            throw new InvalidCredit();
        }

        courseName = name;
        courseCredit = credit;
        status = PLANNING;
        grade = 0;
    }

    // MODIFIES: this
    // EFFECTS: change the course's name to the given name
    //          throw InvalidName exception if name is an empty string
    public void changeCourseName(String name) throws InvalidName {
        if (name.length() == 0) {
            throw new InvalidName();
        }
        courseName = name;
    }

    // MODIFIES: this
    // EFFECTS: change the course's credit to the given credit
    //          throw InvalidCredit exception if credit is <= 0
    public void changeCourseCredit(int credit) throws InvalidCredit {
        if (credit <= 0) {
            throw new InvalidCredit();
        }
        courseCredit = credit;
    }

    // MODIFIES: this
    // EFFECTS: set status to ongoing
    public void setOngoing() {
        status = ONGOING;
    }

    // MODIFIES: this
    // EFFECTS: set status to completed
    public void setCompleted() {
        status = COMPLETED;
    }

    // MODIFIES: this
    // EFFECTS: set status to planning
    public void setPlanning() {
        status = PLANNING;
    }

    // MODIFIES: this
    // EFFECTS: set my course's grade to the given grade & set the course's status to completed
    public int setGrade(int grade) {
        this.grade = grade;
        status = COMPLETED;
        return this.grade;
    }

    // EFFECTS: return the course's name
    public String getCourseName() {
        return courseName;
    }

    // EFFECTS: return the course's credit
    public int getCourseCredit() {
        return courseCredit;
    }

    // EFFECTS: return the status of the course
    public String getCourseStatus() {
        return status;
    }

    // EFFECTS: return the grade gotten in the course
    public int getCourseGrade() {
        return grade;
    }
}
