package model;

import exceptions.EmptyList;
import exceptions.InvalidName;

import java.util.ArrayList;

public class CourseList {

    private String courseListName;
    private ArrayList<Course> listOfCourse;

    // REQUIRES: name to be a non-empty string
    // EFFECTS: initializes a CourseList with given name & empty course in the list
    public CourseList(String name) throws InvalidName {
        if (name.length() == 0) {
            throw new InvalidName();
        }

        courseListName = name;
        listOfCourse = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if course is not in the list, add course to the list and return true
    //          if course is in the list, return false
    public boolean addCourse(Course course) {
        if (isCourseInList(course.getCourseName())) {
            return false;
        } else {
            listOfCourse.add(course);
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: if the list is not empty, remove the last added course in the list and return true
    //          if the list is empty, return false
    public boolean removeRecentCourse() {
        if (isEmpty()) {
            return false;
        } else {
            int size = length() - 1;
            listOfCourse.remove(size);
            return true;
        }
    }

    // MODIFIES: this
    // EFFECTS: if course is in the list, remove course from the list and return true
    //          if course is not in the list, return false
    public boolean removeCourse(String courseName) {
        Course course;
        for (int i = 0; i < length(); i++) {
            course = listOfCourse.get(i);
            if (course.getCourseName() == courseName) {
                listOfCourse.remove(i);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: name not an empty string
    // MODIFIES: this
    // EFFECTS: change the CourseList name to the given name
    public void setCourseListName(String name) throws InvalidName {
        if (name.length() == 0) {
            throw new InvalidName();
        } else {
            courseListName = name;
        }
    }

    // EFFECTS: return the length of the list
    public int length() {
        return listOfCourse.size();
    }

    // REQUIRES: listOfCourse is not empty
    // EFFECTS: return list of courses
    public ArrayList<Course> getListOfCourses() throws EmptyList {
        if (isEmpty()) {
            throw new EmptyList();
        } else {
            return listOfCourse;
        }
    }

    // EFFECTS: if course is in the list, return the requested course
    //          if course is not in the list, return null
    public Course getCourse(String name) {
        for (Course course: listOfCourse) {
            if (course.getCourseName() == name) {
                return course;
            }
        }
        return null;
    }

    // EFFECTS: return true if given course's name is in the list; otherwise false
    public boolean isCourseInList(String name) {
        for (Course course: listOfCourse) {
            if (course.getCourseName() == name) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: return CourseList's name
    public String getName() {
        return courseListName;
    }

    // EFFECTS: return true if the list is empty; otherwise false
    public boolean isEmpty() {
        return listOfCourse.isEmpty();
    }
}
