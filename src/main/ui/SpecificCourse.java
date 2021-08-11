package ui;

import exceptions.EmptyList;
import exceptions.InvalidCredit;
import exceptions.InvalidName;
import model.Course;
import model.CourseList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpecificCourse extends JFrame implements ActionListener {
    private String worklistName;
    private String name;
    private int credit;
    private String status;
    private int grade;
    private Course course;
    private CourseList worklist;
    private String display;

    private JLabel courseName;
    private JLabel courseCredit;
    private JLabel courseStatus;
    private JLabel courseGrade;

    private JPanel courseDetailPanel;
    private JPanel courseChangeDetailPanel;
    private JPanel courseChangeNamePanel;
    private JPanel courseChangeCreditPanel;
    private JPanel courseChangeStatusPanel;
    private JPanel courseChangeGradePanel;

    private JTextField courseChangeName;
    private JTextField courseChangeCredit;
    private JTextField courseChangeGrade;

    private JRadioButton planningStatus;
    private JRadioButton ongoingStatus;
    private JRadioButton completedStatus;

    private JButton courseChangeNameButton;
    private JButton courseChangeCreditButton;
    private JButton courseChangeStatusButton;
    private JButton courseChangeGradeButton;

    // EFFECTS: initializes a new page for a specific course
    public SpecificCourse(String name, CourseList worklist) {
        this.worklistName = name.toUpperCase();
        this.worklist = worklist;
        course = this.worklist.getCourse(this.worklistName);

        makeCourseNameLabel();
        makeCourseCreditLabel();
        makeCourseStatusLabel();
        makeCourseGradeLabel();

        makeCourseDetailPanel();
        makeCourseChangeDetailPanel();

        makeChangeNamePanel();
        makeChangeCreditPanel();
        makeChangeStatusPanel();
        makeChangeGradePanel();

        setSpecificCourseFrame();

        addToCourseDetailPanel();
        addToCourseChangeDetailPanel();

        this.add(courseDetailPanel);
        this.add(courseChangeDetailPanel);

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: set the frame for this page
    private void setSpecificCourseFrame() {
        this.setLayout(null);
        this.setSize(new Dimension(700, 400));
        this.setResizable(false);
        this.setTitle(this.name);
    }

    // MODIFIES: this
    // EFFECTS: add components to the detail panel
    private void addToCourseDetailPanel() {
        courseDetailPanel.add(courseName);
        courseDetailPanel.add(courseCredit);
        courseDetailPanel.add(courseStatus);
        courseDetailPanel.add(courseGrade);
    }

    // MODIFIES: this
    // EFFECTS: add components to the change detail panel
    private void addToCourseChangeDetailPanel() {
        courseChangeDetailPanel.add(courseChangeNamePanel);
        courseChangeDetailPanel.add(courseChangeCreditPanel);
        courseChangeDetailPanel.add(courseChangeStatusPanel);
        courseChangeDetailPanel.add(courseChangeGradePanel);
    }

    // MODIFIES: this
    // EFFECTS: make a new detail panel
    private void makeCourseDetailPanel() {
        courseDetailPanel = new JPanel();
        courseDetailPanel.setBounds(0, 0, 250, 200);
        courseDetailPanel.setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: make the course name label
    private void makeCourseNameLabel() {
        courseName = new JLabel();
        display = "Course name : " + course.getCourseName();
        courseName.setText(display);
        courseName.setBounds(10, 10, 200, 30);
        courseName.setFont(new Font("Batang", Font.PLAIN, 15));
    }

    // MODIFIES: this
    // EFFECTS: make the course credit label
    private void makeCourseCreditLabel() {
        courseCredit = new JLabel();
        display = "Credit           : " + course.getCourseCredit();
        courseCredit.setText(display);
        courseCredit.setBounds(10, 40, 150, 30);
        courseCredit.setFont(new Font("Batang", Font.PLAIN, 15));
    }

    // MODIFIES: this
    // EFFECTS: make the course status label
    private void makeCourseStatusLabel() {
        courseStatus = new JLabel();
        display = "Status          : " + course.getCourseStatus();
        courseStatus.setText(display);
        courseStatus.setBounds(10, 70, 200, 30);
        courseStatus.setFont(new Font("Batang", Font.PLAIN, 15));
    }

    // MODIFIES: this
    // EFFECTS: make the course grade label
    private void makeCourseGradeLabel() {
        courseGrade = new JLabel();
        display = "Grade           : " + course.getCourseGrade();
        courseGrade.setText(display);
        courseGrade.setBounds(10, 100, 150, 30);
        courseGrade.setFont(new Font("Batang", Font.PLAIN, 15));
    }

    // MODIFIES: this
    // EFFECTS: make a new change detail panel
    private void makeCourseChangeDetailPanel() {
        courseChangeDetailPanel = new JPanel();
        courseChangeDetailPanel.setBounds(250, 0, 450, 400);
        courseChangeDetailPanel.setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: make a new change name panel
    private void makeChangeNamePanel() {
        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setBounds(10, 10, 100, 30);

        courseChangeName = new JTextField();
        courseChangeName.setBounds(110, 10, 200, 30);

        courseChangeNameButton = new JButton("Change name");
        courseChangeNameButton.addActionListener(this);
        courseChangeNameButton.setBounds(80, 50, 150, 30);

        courseChangeNamePanel = new JPanel();
        courseChangeNamePanel.setLayout(null);
        courseChangeNamePanel.setBounds(0, 0, 350, 80);

        courseChangeNamePanel.add(courseChangeName);
        courseChangeNamePanel.add(courseChangeNameButton);
        courseChangeNamePanel.add(courseNameLabel);
    }

    // MODIFIES: this
    // EFFECTS: make a new change credit panel
    private void makeChangeCreditPanel() {
        JLabel courseNameLabel = new JLabel("Course Credit:");
        courseNameLabel.setBounds(10, 10, 100, 30);

        courseChangeCredit = new JTextField();
        courseChangeCredit.setBounds(110, 10, 200, 30);

        courseChangeCreditButton = new JButton("Change credit");
        courseChangeCreditButton.addActionListener(this);
        courseChangeCreditButton.setBounds(80, 50, 150, 30);

        courseChangeCreditPanel = new JPanel();
        courseChangeCreditPanel.setLayout(null);
        courseChangeCreditPanel.setBounds(0, 85, 350, 80);

        courseChangeCreditPanel.add(courseChangeCredit);
        courseChangeCreditPanel.add(courseChangeCreditButton);
        courseChangeCreditPanel.add(courseNameLabel);
    }

    // MODIFIES: this
    // EFFECTS: make a new change status panel
    private void makeChangeStatusPanel() {
        JLabel courseNameLabel = new JLabel("Course Status:");
        courseNameLabel.setBounds(10, 10, 100, 30);

        planningStatus = new JRadioButton("planning");
        planningStatus.setBounds(110, 10, 100, 30);

        ongoingStatus = new JRadioButton("ongoing");
        ongoingStatus.setBounds(210, 10, 100, 30);

        completedStatus = new JRadioButton("completed");
        completedStatus.setBounds(310, 10, 100, 30);

        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(planningStatus);
        statusGroup.add(ongoingStatus);
        statusGroup.add(completedStatus);

        courseChangeStatusButton = new JButton("Change status");
        courseChangeStatusButton.addActionListener(this);
        courseChangeStatusButton.setBounds(80, 50, 150, 30);

        courseChangeStatusPanel = new JPanel();
        courseChangeStatusPanel.setLayout(null);
        courseChangeStatusPanel.setBounds(0, 170, 450, 80);

        courseChangeStatusPanel.add(planningStatus);
        courseChangeStatusPanel.add(ongoingStatus);
        courseChangeStatusPanel.add(completedStatus);
        courseChangeStatusPanel.add(courseChangeStatusButton);
        courseChangeStatusPanel.add(courseNameLabel);
    }

    // MODIFIES: this
    // EFFECTS: make a new change grade panel
    private void makeChangeGradePanel() {
        JLabel courseNameLabel = new JLabel("Course Grade:");
        courseNameLabel.setBounds(10, 10, 100, 30);

        courseChangeGrade = new JTextField();
        courseChangeGrade.setBounds(110, 10, 200, 30);

        courseChangeGradeButton = new JButton("Change grade");
        courseChangeGradeButton.addActionListener(this);
        courseChangeGradeButton.setBounds(80, 50, 150, 30);

        courseChangeGradePanel = new JPanel();
        courseChangeGradePanel.setLayout(null);
        courseChangeGradePanel.setBounds(0, 255, 350, 80);

        courseChangeGradePanel.add(courseChangeGrade);
        courseChangeGradePanel.add(courseChangeGradeButton);
        courseChangeGradePanel.add(courseNameLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == courseChangeNameButton) {
            changeCourseName(courseChangeName.getText());
        }
        if (e.getSource() == courseChangeCreditButton) {
            changeCourseCredit(courseChangeCredit.getText());
        }
        if (e.getSource() == courseChangeStatusButton) {
            changeCourseStatus();
        }
        if (e.getSource() == courseChangeGradeButton) {
            changeCourseGrade(courseChangeGrade.getText());
        }

        makeNewSpecificCourseWindow();
    }

    // MODIFIES: this
    // EFFECTS: close this page and open a new (this) page
    private void makeNewSpecificCourseWindow() {
        this.dispose();
        new SpecificCourse(course.getCourseName(), worklist);
    }

    // MODIFIES: this
    // EFFECTS: change the course name
    private void changeCourseName(String newName) {
        this.name = newName.toUpperCase();
        try {
            course.changeCourseName(this.name);
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: change the course credit
    private void changeCourseCredit(String credit) {
        this.credit = Integer.parseInt(credit);
        try {
            course.changeCourseCredit(this.credit);
        } catch (InvalidCredit invalidCredit) {
            invalidCredit.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: change the course status
    private void changeCourseStatus() {
        if (planningStatus.isSelected()) {
            course.setPlanning();
        } else if (ongoingStatus.isSelected()) {
            course.setOngoing();
        } else if (completedStatus.isSelected()) {
            course.setCompleted();
        }
    }

    // MODIFIES: this
    // EFFECTS: change the course grade
    private void changeCourseGrade(String grade) {
        this.grade = Integer.parseInt(grade);;
        course.setGrade(this.grade);
        course.setCompleted();
    }
}
