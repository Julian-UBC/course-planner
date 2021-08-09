package ui;

import exceptions.EmptyList;
import exceptions.InvalidCredit;
import exceptions.InvalidName;
import model.Course;
import model.CourseList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CoursePlanningPage extends JFrame implements ListSelectionListener, ActionListener {

    private static final String JSON_STORE = "./data/courseList.json";
    private CourseList worklist;
    private Course course;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private boolean isSuccessful;
    private String name;
    private int credit;

    private JList<Course> courses;
    private DefaultListModel<Course> coursesModel;

    private JButton addCourseButton;
    private JButton removeCourseButton;
    private JButton accessCourseButton;

    private JScrollPane coursesScrollPane;
    private JPanel inputPanel;
    private JPanel modifyPanel;
    private JPanel addCoursePanel;
    private JPanel removeCoursePanel;
    private JPanel accessCoursePanel;

    private JTextField addCourseName;
    private JTextField addCourseCredit;
    private JTextField removeCourseName;
    private JTextField accessCourseName;

    private JMenuBar menuBar;
    private JMenu fileMenu;

    private JMenuItem loadItem;
    private JMenuItem saveItem;
    private JMenuItem exitItem;

    public CoursePlanningPage() {

        initializeJson();
        initializeDefaultWorklist();

        coursesModel = new DefaultListModel<>();

        courses = new JList<>(coursesModel);
//        courses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        courses.setSelectedIndex(0);
//        courses.addListSelectionListener(this);
        courses.setVisibleRowCount(5);

        makeAddCoursePanel();
        makeRemoveCoursePanel();
        makeAccessSpecificCoursePanel();

        coursesScrollPane = new JScrollPane(courses);
        coursesScrollPane.setBounds(0,0,500,200);

        initializeModifyPanel();
        initializeInputPanel();

        inputPanel.add(addCoursePanel);
        inputPanel.add(removeCoursePanel);
        inputPanel.add(accessCoursePanel);

        initializeMenuBar();
        setCoursePlanningFrame();

        this.add(coursesScrollPane);
        this.add(modifyPanel);
        this.add(inputPanel);
        this.setJMenuBar(menuBar);

        this.setVisible(true);
    }

    private void initializeMenuBar() {
        fileMenu = new JMenu("File");

        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        menuBar = new JMenuBar();
        menuBar.setBackground(Color.GREEN);
        menuBar.setOpaque(true);
        menuBar.add(fileMenu);

    }

    private void initializeModifyPanel() {
        modifyPanel = new JPanel();
        modifyPanel.setBounds(0, 200, 500, 300);
        modifyPanel.setBackground(Color.GREEN);
    }

    private void initializeInputPanel() {
        inputPanel = new JPanel();
        inputPanel.setBounds(500, 0, 1000, 500);
        inputPanel.setBackground(Color.BLUE);
        inputPanel.setLayout(null);
    }

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

    private void initializeDefaultWorklist() {
        try {
            worklist = new CourseList("Worklist 1");
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }
    }

    private void initializeJson() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    private void setCoursePlanningFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(1000, 500));
//        this.setResizable(false);
        this.setTitle(worklist.getName());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

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
            addNewCourse(addCourseName.getText(), addCourseCredit.getText());
            addCourseName.setText("");
            addCourseCredit.setText("");
        }
        if (e.getSource() == removeCourseButton) {
            removeCourse(removeCourseName.getText());
            removeCourseName.setText("");
        }
        if (e.getSource() == accessCourseButton) {
            SpecificCourse specificCourse = new SpecificCourse(accessCourseName.getText(), worklist);
            accessCourseName.setText("");
        }
        if (e.getSource() == loadItem) {
            loadMyWorklist();
        }
        if (e.getSource() == saveItem) {
            saveMyWorklist();
        }
        if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }

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
            invalidName.printStackTrace();
        } catch (InvalidCredit invalidCredit) {
            invalidCredit.printStackTrace();
        }
    }

    private void removeCourse(String name) {
        this.name = name.toUpperCase();
        course = worklist.getCourse(this.name);
        isSuccessful = worklist.removeCourse(this.name);
        if (isSuccessful) {
            coursesModel.removeElement(course);
        }
    }
}
