package ui;

import exceptions.EmptyList;
import exceptions.InvalidCredit;
import exceptions.InvalidName;
import model.Course;
import model.CourseList;

import java.util.ArrayList;
import java.util.Scanner;

// Course Planning application
public class CoursePlanningApp {

    private Scanner input;
    private CourseList worklist;
    private Course course;

    private String name;
    private int credit;
    private ArrayList<Course> courses;
    private String command;
    private boolean keepGoing;
    private boolean keepItGoing;

    // EFFECTS: runs the Course Planning application
    public CoursePlanningApp() {
        runCoursePlanning();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runCoursePlanning() {
        keepGoing = true;
        command = null;
        input = new Scanner(System.in);
        makeDefaultWorklist();

        while (keepGoing) {
            displayMenu();
            command = input.nextLine().toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you for using Course Planning!! \nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: make a default worklist
    private void makeDefaultWorklist() {
        try {
            worklist = new CourseList("Worklist 1");
        } catch (InvalidName invalidName) {
            System.out.println("System failed");
            // do nothing because it won't happened
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            createNewWorklist();
        } else if (command.equals("a")) {
            addingCourse();
        } else if (command.equals("r")) {
            removingCourse();
        } else if (command.equals("g")) {
            myWorklistName();
        } else if (command.equals("ch")) {
            changeWorklistName();
        } else if (command.equals("s")) {
            seeMyWorklist();
        } else {
            System.out.println("Input invalid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: create new worklist
    private void createNewWorklist() {

        System.out.println("New Worklist Name:");
        name = input.nextLine();

        try {
            worklist = new CourseList(name);

            System.out.println(name + " successfully created");
        } catch (InvalidName invalidName) {
            System.out.println("Invalid Name \nPlease try again");
            createNewWorklist();
        }

    }

    // MODIFIES: this
    // EFFECTS: create a new course and add it to worklist
    private void addingCourse() {
        System.out.println("Add a Course");

        System.out.println("Course name:");
        name = input.nextLine().toUpperCase();

        System.out.println("Course credit:");
        credit = Integer.parseInt(input.nextLine());

        try {
            course = new Course(name, credit);

            if (!worklist.isCourseInList(name)) {
                worklist.addCourse(course);
                System.out.println(name + " successfully added");
            }
        } catch (InvalidName invalidName) {
            System.out.println("\nInvalid name \nPlease try again");
            addingCourse();
        } catch (InvalidCredit invalidCredit) {
            System.out.println("\nInvalid credit \nPlease try again");
            addingCourse();
        }
    }

    // MODIFIES: this
    // EFFECTS: remove a course from worklist
    private void removingCourse() {
    }

    // MODIFIES: this
    // EFFECTS: get my worklist name
    private void myWorklistName() {
        name = worklist.getName();
        System.out.println(name);
    }

    // MODIFIES: this
    // EFFECTS: change my worlist name
    private void changeWorklistName() {
        System.out.println("Change " + worklist.getName() + " to:");
        name = input.nextLine();

        try {
            worklist.setCourseListName(name);
            System.out.println(name + " successfully changed");
        } catch (InvalidName invalidName) {
            System.out.println("\nInvalid name \nPlease try again");
            changeWorklistName();
        }


    }

    private void seeMyWorklist() {
        try {
            courses = worklist.getListOfCourses();

            for (int i = 0; i < courses.size(); i++) {
                course = courses.get(i);
                System.out.println(course.getCourseName());
            }

            keepItGoing = true;

            while (keepItGoing) {
                displayCourseMenu();
                command = input.nextLine();

                if (command.equals("b")) {
                    keepItGoing = false;
                } else {
                    processCourseCommand(command);
                }
            }

        } catch (EmptyList emptyList) {
            System.out.println("No course found in " + worklist.getName());
        }


    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc  -> Create New Worklist");
        System.out.println("\ta  -> Add Course");
        System.out.println("\tr  -> Remove Course");
        System.out.println("\tg  -> My Worklist Name");
        System.out.println("\tch -> Change Worklist Name");
        System.out.println("\ts  -> See My Worklist");
        System.out.println("\tq  -> Close App");
    }

    // EFFECTS: display course menu of options to user
    private void displayCourseMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Access my course");
        System.out.println("\tb -> Back");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCourseCommand(String command) {
        int commandNum = Integer.parseInt(command);
        if (commandNum == 1) {
            getSpecificCourse();
        } else {
            System.out.println("Input invalid...");
        }
    }

    // EFFECTS: display specific course detail to user
    private void getSpecificCourse() {
        System.out.println("\nGet specific detail of courses \nCourse name:");
        command = input.nextLine().toUpperCase();
        course = worklist.getCourse(command);

        if (course == null) {
            System.out.println("Course not found");
        } else {
            System.out.println("\nCourse name: " + course.getCourseName());
            System.out.println("Course credit: " + course.getCourseCredit());
            System.out.println("Course status: " + course.getCourseStatus());
            System.out.println("Course grade: " + course.getCourseGrade());
        }
    }

}











