package ui;

import exceptions.EmptyList;
import exceptions.InvalidCredit;
import exceptions.InvalidName;
import model.Course;
import model.CourseList;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tools.BackgroundMenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Citation: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html -> List Demo Project
// Main page of Course Planning application with GUI
public class CoursePlanningPage extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/courseList.json";
    private CourseList worklist;
    private Course course;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private boolean isSuccessful;
    private String name;
    private int credit;
    private String worklistName = "Worklist 1";

    private JList<Course> courses;
    private DefaultListModel<Course> coursesModel;

    private JButton addCourseButton;
    private JButton removeCourseButton;
    private JButton accessCourseButton;
    private JButton renameWorklistButton;

    private JScrollPane coursesScrollPane;
    private JPanel inputPanel;
    private JPanel modifyPanel;
    private JPanel addCoursePanel;
    private JPanel removeCoursePanel;
    private JPanel accessCoursePanel;
    private JPanel renameWorklistPanel;

    private JTextField addCourseName;
    private JTextField addCourseCredit;
    private JTextField removeCourseName;
    private JTextField accessCourseName;
    private JTextField renameWorklist;

    private BackgroundMenuBar menuBar;
    private JMenu fileMenu;

    private JMenuItem newItem;
    private JMenuItem loadItem;
    private JMenuItem saveItem;
    private JMenuItem exitItem;

    // EFFECTS: initializes the main page for Course Planning App
    public CoursePlanningPage() {
        initializeNewWorklist();
        runCoursePlanningApp();
    }

    // EFFECTS: initializes the main page for Course Planning App with the given worklist name
    public CoursePlanningPage(String worklistName) {
        this.worklistName = worklistName;
        initializeNewWorklist();
        runCoursePlanningApp();
    }

    // MODIFIES: this
    // EFFECTS: run the course planning app
    private void runCoursePlanningApp() {
        initializeJson();

        coursesModel = new DefaultListModel<>();

        initializeCourses();

        makeAddCoursePanel();
        makeRemoveCoursePanel();
        makeAccessSpecificCoursePanel();
        makeChangeWorklistNamePanel();
        makeCoursesScrollPane();

//        initializeModifyPanel();
        initializeInputPanel();
        addToInputPanel();

        initializeMenuBar();
        setCoursePlanningFrame();

        addToThisFrame();

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: add component to (this) frame
    private void addToThisFrame() {
        this.add(coursesScrollPane);
//        this.add(modifyPanel);
        this.add(inputPanel);
        this.setJMenuBar(menuBar);
    }

    // MODIFIES: this
    // EFFECTS: add component to input panel
    private void addToInputPanel() {
        inputPanel.add(addCoursePanel);
        inputPanel.add(removeCoursePanel);
        inputPanel.add(accessCoursePanel);
        inputPanel.add(renameWorklistPanel);
    }

    // MODIFIES: this
    // EFFECTS: make a panel for displaying list of courses
    private void makeCoursesScrollPane() {
        coursesScrollPane = new JScrollPane(courses);
        coursesScrollPane.setBounds(0,0,500,200);
    }

    // MODIFIES: this
    // EFFECTS: initializes JList for courses
    private void initializeCourses() {
        courses = new JList<>(coursesModel);
//        courses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        courses.setSelectedIndex(0);
//        courses.addListSelectionListener(this);
//        courses.setVisibleRowCount(5);
        courses.setFont(new Font("Batang", Font.PLAIN, 15));
    }

    // MODIFIES: this
    // EFFECTS: initializes a new menu bar
    private void initializeMenuBar() {
        fileMenu = new JMenu("File");
        fileMenu.setOpaque(false);

        newItem = new JMenuItem("New Worklist");
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        newItem.setBackground(new Color(0xb3bcc9));
        loadItem.setBackground(new Color(0xb3bcc9));
        saveItem.setBackground(new Color(0xb3bcc9));
        exitItem.setBackground(new Color(0xb3bcc9));

        newItem.addActionListener(this);
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(newItem);
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        menuBar = new BackgroundMenuBar();
        menuBar.add(fileMenu);
        menuBar.setColor(new Color(0xb3bcc9));

    }

    private void initializeModifyPanel() {
        modifyPanel = new JPanel();
        modifyPanel.setBounds(0, 200, 500, 300);
        modifyPanel.setBackground(Color.GREEN);
    }

    // MODIFIES: this
    // EFFECTS: make a new input panel
    private void initializeInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setBounds(500, 0, 1000, 500);
        inputPanel.setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: make a panel for adding course
    private void makeAddCoursePanel() {
        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setBounds(10, 10, 100, 30);

        JLabel courseCreditLabel = new JLabel("Course Credit:");
        courseCreditLabel.setBounds(10, 50, 100, 30);

        addCourseName = new JTextField();
        addCourseName.setBounds(110, 10, 200, 30);

        addCourseCredit = new JTextField();
        addCourseCredit.setBounds(110, 50, 200, 30);

        addCourseButton = new JButton("Add Course");
        addCourseButton.addActionListener(this);
        addCourseButton.setBounds(95, 90, 120, 30);

        addCoursePanel = new JPanel();
        addCoursePanel.setLayout(null);
        addCoursePanel.setBounds(0, 0, 350, 120);

        addCoursePanel.add(addCourseName);
        addCoursePanel.add(addCourseCredit);
        addCoursePanel.add(addCourseButton);
        addCoursePanel.add(courseNameLabel);
        addCoursePanel.add(courseCreditLabel);
    }

    // MODIFIES: this
    // EFFECTS: make a panel for removing course
    private void makeRemoveCoursePanel() {
        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setBounds(10, 10, 100, 30);

        removeCourseName = new JTextField();
        removeCourseName.setBounds(110, 10, 200, 30);

        removeCourseButton = new JButton("Remove Course");
        removeCourseButton.addActionListener(this);
        removeCourseButton.setBounds(80, 50, 150, 30);

        removeCoursePanel = new JPanel();
        removeCoursePanel.setLayout(null);
        removeCoursePanel.setBounds(0, 130, 350, 80);

        removeCoursePanel.add(removeCourseName);
        removeCoursePanel.add(removeCourseButton);
        removeCoursePanel.add(courseNameLabel);
    }

    // MODIFIES: this
    // EFFECTS: make a panel for accessing specific course
    private void makeAccessSpecificCoursePanel() {
        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setBounds(10, 10, 100, 30);

        accessCourseName = new JTextField();
        accessCourseName.setBounds(110, 10, 200, 30);

        accessCourseButton = new JButton("Access Course");
        accessCourseButton.addActionListener(this);
        accessCourseButton.setBounds(80, 50, 150, 30);

        accessCoursePanel = new JPanel();
        accessCoursePanel.setLayout(null);
        accessCoursePanel.setBounds(0, 220, 350, 80);

        accessCoursePanel.add(accessCourseName);
        accessCoursePanel.add(accessCourseButton);
        accessCoursePanel.add(courseNameLabel);
    }

    // MODIFIES: this
    // EFFECTS: make a panel for changing worklist name
    private void makeChangeWorklistNamePanel() {
        JLabel courseNameLabel = new JLabel("Worklist Name:");
        courseNameLabel.setBounds(10, 10, 100, 30);

        renameWorklist = new JTextField();
        renameWorklist.setBounds(110, 10, 200, 30);

        renameWorklistButton = new JButton("Rename Worklist");
        renameWorklistButton.addActionListener(this);
        renameWorklistButton.setBounds(80, 50, 150, 30);

        renameWorklistPanel = new JPanel();
        renameWorklistPanel.setLayout(null);
        renameWorklistPanel.setBounds(0, 310, 350, 80);

        renameWorklistPanel.add(renameWorklist);
        renameWorklistPanel.add(renameWorklistButton);
        renameWorklistPanel.add(courseNameLabel);
    }

    // MODIFIES: this
    // EFFECTS: initializes new worklist
    private void initializeNewWorklist() {
        try {
            worklist = new CourseList(this.worklistName);
        } catch (InvalidName invalidName) {
            // do nothing
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes Json
    private void initializeJson() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: set the frame for this page
    private void setCoursePlanningFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(1000, 500));
//        this.setResizable(false);
        this.setTitle(worklist.getName());
    }

//    @Override
//    public void valueChanged(ListSelectionEvent e) {
//
//    }

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
            for (Course c: worklist.getListOfCourses()) {
                coursesModel.addElement(c);
            }

            this.setTitle(worklist.getName());
            System.out.println("Loaded " + worklist.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } catch (EmptyList emptyList) {
            emptyList.printStackTrace();
        } catch (Exception e) {
            // won't happen
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseButton) {
            processAddNewCourse();
        } else if (e.getSource() == removeCourseButton) {
            removeCourse(removeCourseName.getText());
            removeCourseName.setText("");
        } else if (e.getSource() == accessCourseButton) {
            new SpecificCourse(accessCourseName.getText(), worklist);
            accessCourseName.setText("");
        } else if (e.getSource() == newItem) {
            dispose();
            new InputNewWorklistName();
        } else if (e.getSource() == loadItem) {
            loadMyWorklist();
        } else if (e.getSource() == saveItem) {
            saveMyWorklist();
        } else if (e.getSource() == exitItem) {
            dispose();
            new ThankYouPage();
        } else if (e.getSource() == renameWorklistButton) {
            renameWorklist(renameWorklist.getText());
            renameWorklist.setText("");
        }
    }

    // MODIFIES: this
    // EFFECTS: process the adding course feature
    private void processAddNewCourse() {
        addNewCourse(addCourseName.getText(), addCourseCredit.getText());
        addCourseName.setText("");
        addCourseCredit.setText("");
    }

    // MODIFIES: this
    // EFFECTS: process the rename worklist feature
    private void renameWorklist(String name) {
        this.name = name;
        try {
            worklist.setCourseListName(this.name);
            this.setTitle(worklist.getName());
        } catch (InvalidName invalidName) {
            // do nothing
        }
    }

    // MODIFIES: this
    // EFFECTS: add new course to the list of courses
    private void addNewCourse(String name, String credit) {
        this.name = name.toUpperCase();
        this.credit = Integer.parseInt(credit);
        try {
            course = new Course(this.name, this.credit);
            isSuccessful = worklist.addCourse(course);

            if (isSuccessful) {
                coursesModel.addElement(course);
            }
        } catch (InvalidName invalidName) {
            // do nothing
        } catch (InvalidCredit invalidCredit) {
            // do nothing
        }
    }

    // MODIFIES: this
    // EFFECTS: remove a course from the list of courses
    private void removeCourse(String name) {
        this.name = name.toUpperCase();
        course = worklist.getCourse(this.name);
        isSuccessful = worklist.removeCourse(this.name);
        if (isSuccessful) {
            coursesModel.removeElement(course);
        }
    }
}
