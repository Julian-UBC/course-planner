package ui;

import exceptions.EmptyList;
import exceptions.InvalidCredit;
import exceptions.InvalidName;
import model.Course;
import model.CourseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Citation: Some of the code in this class are similar to TellerApp (C1 Lecture Lab)
//           and JsonSerializationDemo (given in the project phase 2)
// Course Planning application with console UI
public class CoursePlanningApp {

    private static final String JSON_STORE = "./data/courseList.json";
    private Scanner input;
    private CourseList worklist;
    private Course course;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

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

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

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
        } else if (command.equals("m")) {
            seeMyWorklist();
        } else if (command.equals("s")) {
            saveMyWorklist();
        } else if (command.equals("l")) {
            loadMyWorklist();
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
        try {
            credit = Integer.parseInt(input.nextLine());

            course = new Course(name, credit);
            isSuccessful = worklist.addCourse(course);

            if (isSuccessful) {
                System.out.println(name + " successfully added");
            } else {
                System.out.println(name + " is already in " + worklist.getName());
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println("That is not a number \n Please try again");
            addingCourse();
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

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processRemoveCommand(String command) {
        try {
            commandNum = Integer.parseInt(command);

            if (commandNum == 1) {
                processRemoveRecentCourse();
            } else if (commandNum == 2) {
                processRemoveCourse();
            } else if (commandNum == 9) {
                seeMyWorklist();
            } else {
                System.out.println("Invalid input...");
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Invalid input...");
        }
    }

    // MODIFIES: this
    // EFFECTS: remove recent added course from worklist
    private void processRemoveRecentCourse() {
        try {
            course = worklist.removeRecentCourse();
            System.out.println(course.getCourseName() + " successfully removed");
        } catch (EmptyList emptyList) {
            System.out.println(worklist.getName() + " is empty");
        }
    }

    // MODIFIES: this
    // EFFECTS: remove intended course from worklist
    private void processRemoveCourse() {
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
        System.out.println("\tm  -> See My Worklist");
        System.out.println("\ts  -> Save My Worklist");
        System.out.println("\tl  -> Load My Worklist");
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
        try {
            commandNum = Integer.parseInt(command);
            if (commandNum == 1) {
                getSpecificCourse();
            } else if (commandNum == 2) {
                removingCourse();
            } else {
                System.out.println("Input invalid...");
            }
        } catch (NumberFormatException numberFormatException) {
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
            changeCourseDetail(course);
            seeMyWorklist();
        }
    }

    // MODIFIES: this
    // EFFECTS: change course detail
    private void changeCourseDetail(Course course) {
        keepItGoing = true;

        while (keepItGoing) {
            System.out.println("\nCourse name: " + course.getCourseName());
            System.out.println("Course credit: " + course.getCourseCredit());
            System.out.println("Course status: " + course.getCourseStatus());
            System.out.println("Course grade: " + course.getCourseGrade());

            displayChangeCourseDetailMenu();
            command = input.nextLine();

            if (command.equals("b")) {
                keepItGoing = false;
            } else {
                processChangeCourseDetailCommand(command, course);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processChangeCourseDetailCommand(String command, Course course) {
        try {
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
                System.out.println("Invalid input...");
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Invalid input...");
        }
    }

    // MODIFIES: this
    // EFFECTS: change selected course name
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

    // MODIFIES: this
    // EFFECTS: change selected course credit
    private void changeCourseCredit(Course course) {
        System.out.println("Change " + course.getCourseName() + " credit to:");

        try {
            credit = Integer.parseInt(input.nextLine());
            course.changeCourseCredit(credit);
            System.out.println(course.getCourseName() + " credit successfully changed to " + credit);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("That is not a number \nPlease try again");
            changeCourseCredit(course);
        } catch (InvalidCredit invalidCredit) {
            System.out.println("Invalid credit \nTry again");
            changeCourseCredit(course);
        }
    }

    // MODIFIES: this
    // EFFECTS: change selected course status
    private void changeCourseStatus(Course course) {
        displayChangeCourseStatusMenu();
        command = input.nextLine();

        if (command.equals("c")) {
            // do nothing
        } else {
            processChangeCourseStatusCommand(command, course);
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user command and change selected course status
    private void processChangeCourseStatusCommand(String command, Course course) {
        try {
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
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Input invalid...");
        }

    }

    // EFFECTS: display menu of change course status options to user
    private void displayChangeCourseStatusMenu() {
        System.out.println("\nChoose from:");
        System.out.println("\t1 -> Change to ongoing");
        System.out.println("\t2 -> Change to completed");
        System.out.println("\t3 -> Change to planning");
        System.out.println("\tc -> Cancel");
    }

    // MODIFIES: this
    // EFFECTS: change selected course grade
    private void changeCourseGrade(Course course) {
        System.out.println("Have you finished with " + course.getCourseName() + "?");
        System.out.println("y for yes");
        System.out.println("n for no");
        command = input.nextLine();

        if (command.equals("y")) {
            name = course.getCourseName();
            System.out.println("What's the grade for " + name + "?");
            try {
                commandNum = Integer.parseInt(input.nextLine());
                course.setGrade(commandNum);
                System.out.println(name + " is set to " + course.getCourseStatus() + " with grade " + commandNum);
            } catch (NumberFormatException numberFormatException) {
                System.out.println("That's not a number \nPlease try again");
                changeCourseGrade(course);
            }
        } else if (command.equals("n")) {
            System.out.println("You can't set grade for a course you haven't finished");
        } else {
            System.out.println("Input invalid...");
        }
    }

    // EFFECTS: display menu of change course detail options to user
    private void displayChangeCourseDetailMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Change course name");
        System.out.println("\t2 -> Change course credit");
        System.out.println("\t3 -> Change course status");
        System.out.println("\t4 -> Change course grade");
        System.out.println("\tb -> Back");
    }

    // EFFECTS: saves the worklist to file
    private void saveMyWorklist() {
        try {
            jsonWriter.open();
            jsonWriter.write(worklist);
            jsonWriter.close();
            System.out.println("Saved " + worklist.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads worklist from file
    private void loadMyWorklist() {
        try {
            worklist = jsonReader.read();
            System.out.println("Loaded " + worklist.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (Exception e) {
            // won't happen
        }
    }

}











