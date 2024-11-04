package UI;

import javax.swing.*;

public class MainScreen extends JFrame{
    public MainScreen() {
        super("Main Window"); // Set title using super constructor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window

        setVisible(true); // Make the JFrame visible
    }
}
