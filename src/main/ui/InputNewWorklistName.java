package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Input name to create new worklist page
public class InputNewWorklistName extends JFrame implements ActionListener {

    private JTextField newWorklistName;
    private JButton newWorklistNameButton;

    // EFFECTS: initializes the page for input a new worklist name
    public InputNewWorklistName() {
        JLabel courseNameLabel = new JLabel("Worklist Name:");
        courseNameLabel.setBounds(30, 50, 100, 30);

        newWorklistName = new JTextField();
        newWorklistName.setBounds(130, 50, 200, 30);

        newWorklistNameButton = new JButton("Create New Worklist");
        newWorklistNameButton.addActionListener(this);
        newWorklistNameButton.setBounds(85, 90, 180, 30);

        this.add(newWorklistName);
        this.add(newWorklistNameButton);
        this.add(courseNameLabel);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(360, 200));
        this.setResizable(false);
        this.setTitle("New Worklist");
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newWorklistNameButton) {
            new CoursePlanningPage(newWorklistName.getText());
            dispose();
        }
    }
}
