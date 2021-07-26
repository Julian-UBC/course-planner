package ui;

import exceptions.EmptyList;
import exceptions.InvalidCredit;
import exceptions.InvalidName;
import model.Course;
import model.CourseList;

import java.util.ArrayList;
import java.util.Scanner;

// Citation: Some of the code in this class are similar to TellerApp (C1 Lecture Lab)
// Course Planning application
public class CoursePlanningApp {

    private Scanner input;
    private CourseList worklist;
    private Course course;

    private String name;
    private int credit;
    private ArrayList<Course> courses;
    private String command;
    private int commandNum;
    private boolean keepGoing;
    private boolean keepItGoing;
    private boolean isSuccessful;


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
            isSuccessful = worklist.addCourse(course);

            if (isSuccessful) {
                System.out.println(name + " successfully added");
            } else {
                System.out.println(name + " is already in " + worklist.getName());
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
        keepItGoing = true;

        while (keepItGoing) {
            displayRemoveMenu();
            command = input.nextLine();

            if (command.equals("b")) {
                keepItGoing = false;
            } else {
                processRemoveCommand(command);
            }
        }
    }

    private void processRemoveCommand(String command) {
        commandNum = Integer.parseInt(command);

        if (commandNum == 1) {
            worklist.removeRecentCourse();
            // print out what course is removed
        } else if (commandNum == 2) {
            System.out.println("Remove course:");
            command = input.nextLine().toUpperCase();

            if (command.length() == 0) {
                System.out.println("Invalid input...");
            } else {
                isSuccessful = worklist.removeCourse(command);

                if (isSuccessful) {
                    System.out.println(command + " successfully removed");
                } else {
                    System.out.println(command + " not found in " + worklist.getName());
                }
            }
        } else if (commandNum == 9) {
            seeMyWorklist();
        } else {
            System.out.println("Invalid input...");
        }
    }

    // EFFECTS: display menu of remove options to user
    private void displayRemoveMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Remove recent added course");
        System.out.println("\t2 -> Remove specific course");
        System.out.println("\t9 -> See my worklist");
        System.out.println("\tb -> Home");
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

    // EFFECTS: display list of my courses in worklist
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

    // EFFECTS: display menu of course options to user
    private void displayCourseMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Access my course");
        System.out.println("\t2 -> Remove my course");
        System.out.println("\tb -> Home");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCourseCommand(String command) {
        commandNum = Integer.parseInt(command);
        if (commandNum == 1) {
            getSpecificCourse();
        } else if (commandNum == 2) {
            removingCourse();
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

            changeCourseDetail(course);
        }
    }

    // MODIFIES: this
    // EFFECTS: change course detail
    private void changeCourseDetail(Course course) {
        keepItGoing = true;

        while (keepItGoing) {
            displayChangeCourseDetailMenu();
            command = input.nextLine();

            if (command.equals("b")) {
                keepItGoing = false;
            } else {
                processChangeCourseDetailCommand(command, course);
            }
        }
    }

    private void processChangeCourseDetailCommand(String command, Course course) {
        commandNum = Integer.parseInt(command);

        if (commandNum == 1) {
            changeCourseName(course);
        } else if (commandNum == 2) {
            changeCourseCredit(course);
        } else if (commandNum == 3) {
            changeCourseStatus(course);
        } else if (commandNum == 4) {
            changeCourseGrade(course);
        } else {
            System.out.println("Invalid input");
        }
    }

    private void changeCourseName(Course course) {
        System.out.println("Change " + course.getCourseName() + " to:");
        String oldName = course.getCourseName();
        name = input.nextLine().toUpperCase();

        try {
            course.changeCourseName(name);
            System.out.println(oldName + " successfully changed to " + name);
        } catch (InvalidName invalidName) {
            System.out.println("Invalid name \nTry again");
            changeCourseName(course);
        }
    }

    private void changeCourseCredit(Course course) {
        System.out.println("Change " + course.getCourseName() + " credit to:");
        credit = Integer.parseInt(input.nextLine());

        try {
            course.changeCourseCredit(credit);
            System.out.println(course.getCourseName() + " credit successfully changed to " + credit);
        } catch (InvalidCredit invalidCredit) {
            System.out.println("Invalid credit \nTry again");
            changeCourseCredit(course);
        }
    }

    private void changeCourseStatus(Course course) {
        displayChangeCourseStatusMenu();
        command = input.nextLine();

        if (command.equals("c")) {
            // do nothing
        } else {
            processChangeCourseStatusCommand(command, course);
        }

    }

    private void processChangeCourseStatusCommand(String command, Course course) {
        commandNum = Integer.parseInt(command);

        if (commandNum == 1) {
            course.setOngoing();
            System.out.println(course.getCourseName() + " status successfully set to " + course.getCourseStatus());
        } else if (commandNum == 2) {
            course.setCompleted();
            System.out.println(course.getCourseName() + " status successfully set to " + course.getCourseStatus());
        } else if (commandNum == 3) {
            course.setPlanning();
            System.out.println(course.getCourseName() + " status successfully set to " + course.getCourseStatus());
        } else {
            System.out.println("Input invalid...");
        }
    }

    private void displayChangeCourseStatusMenu() {
        System.out.println("\nChoose from:");
        System.out.println("\t1 -> Change to ongoing");
        System.out.println("\t2 -> Change to completed");
        System.out.println("\t3 -> Change to planning");
        System.out.println("\tc -> Cancel");
    }

    private void changeCourseGrade(Course course) {
        System.out.println("Have you finished with " + course.getCourseName() + "?");
        System.out.println("y for yes");
        System.out.println("n for no");
        command = input.nextLine();

        if (command.equals("y")) {
            name = course.getCourseName();
            System.out.println("What's the grade for " + name + "?");
            commandNum = Integer.parseInt(input.nextLine());
            course.setGrade(commandNum);
            System.out.println(name + " is set to " + course.getCourseStatus() + " with grade " + commandNum);
        } else if (command.equals("n")) {
            System.out.println("You can't set grade for a course you haven't finished");
        } else {
            System.out.println("Input invalid...");
        }
    }

    private void displayChangeCourseDetailMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Change course name");
        System.out.println("\t2 -> Change course credit");
        System.out.println("\t3 -> Change course status");
        System.out.println("\t4 -> Change course grade");
        System.out.println("\tb -> Back");
    }

}











