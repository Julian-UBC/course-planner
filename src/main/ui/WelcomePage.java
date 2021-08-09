package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

// Welcome page of Course Planning application with GUI
public class WelcomePage extends JFrame implements ActionListener {
    private static final String OPEN_APP = "Open Course Planning";
    private JButton openAppButton;

    public WelcomePage() {
        JPanel welcomePanel = makeWelcomePanel();
        JPanel openAppPanel = makeOpenAppButton();

        setWelcomePageFrame();

        this.add(welcomePanel);
        this.add(openAppPanel);

        this.setVisible(true);
    }

    private void setWelcomePageFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(800, 500));
        this.setResizable(false);
        this.setTitle("Course Planning App");
    }

    private JPanel makeWelcomePanel() {
        ImageIcon welcomeImage = new ImageIcon("data/welcome.jpg");

        JLabel welcomeLabel = new JLabel();
        welcomeLabel.setIcon(welcomeImage);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setBounds(0, 50, 800, 300);
        welcomePanel.setLayout(new FlowLayout());
        welcomePanel.add(welcomeLabel);
        return welcomePanel;
    }

    private JPanel makeOpenAppButton() {
        openAppButton = new JButton();
        openAppButton.addActionListener(this);
        openAppButton.setMargin(new Insets(10, 10, 10, 10));
        openAppButton.setText(OPEN_APP);
        openAppButton.setFont(new Font("Arial", Font.PLAIN, 25));
        openAppButton.setForeground(new Color(0x19a78f));
        openAppButton.setBackground(new Color(0xffebe4));
        openAppButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createLineBorder(new Color(0xffebe4), 10)));
        openAppButton.setOpaque(true);

        JPanel openAppPanel = new JPanel();
        openAppPanel.setBounds(0, 350, 800, 100);
        openAppPanel.setLayout(new FlowLayout());
        openAppPanel.add(openAppButton);
        return openAppPanel;
    }

    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openAppButton) {
            playSound("data/WelcomeSound.wav");
            this.dispose();
            CoursePlanningPage myFrame = new CoursePlanningPage();
        }
    }
}
