package UI;

import javax.swing.*;

public class MainScreen {
    JFrame frame = new JFrame("Main Window");

    public MainScreen() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(500,400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

}
