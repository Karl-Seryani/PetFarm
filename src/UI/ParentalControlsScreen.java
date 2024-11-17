package UI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ParentalControlsScreen {
    private JFrame frame;
    private JPanel mainPanel, controlPanel;
    private JPasswordField passwordField;
    private JButton loginButton, setRestrictionsButton, resetStatsButton, revivePetButton, playGameButton;
    private JCheckBox enableRestrictionsCheckBox;
    private JTextField startTimeField, endTimeField;
    private JLabel statusLabel, playtimeLabel, avgSessionLabel;
    private int totalPlayTime = 0; // Placeholder for total playtime in hours
    private int sessionCount = 1;  // Placeholder for the number of sessions

    // Reference to MainScreen
    private MainScreen mainScreen;

    // Hardcoded password
    private static final String HARDCODED_PASSWORD = "myPassword"; // Replace with your password

    // List of pets
    private List<Pet> pets = new ArrayList<>();

    public ParentalControlsScreen(MainScreen mainScreen) {
        this.mainScreen = mainScreen;

        // Set up the frame
        frame = new JFrame("Parental Controls");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Password protection
        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        statusLabel = new JLabel("Please enter the password to continue.");
        statusLabel.setForeground(Color.RED);

        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(statusLabel);

        // Action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                if (password.equals(HARDCODED_PASSWORD)) { // Verify with hardcoded password
                    showParentalControls();
                } else {
                    statusLabel.setText("Incorrect password. Try again.");
                }
            }
        });

        frame.add(mainPanel);
        frame.setSize(450, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the frame when closed
        frame.setVisible(true);

        // Hide the MainScreen while ParentalControlsScreen is active
        mainScreen.setVisible(false);

        // Add a window listener to show the MainScreen when this frame is closed
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mainScreen.setVisible(true); // Make MainScreen visible again
            }
        });
    }

    private void showParentalControls() {
        // Clear the main panel and set up the control panel
        mainPanel.removeAll();
        controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        // Parental Limitations Section
        JPanel restrictionsPanel = new JPanel();
        restrictionsPanel.setLayout(new GridLayout(3, 2, 10, 10));
        restrictionsPanel.setBorder(new TitledBorder("Playtime Restrictions"));

        enableRestrictionsCheckBox = new JCheckBox("Enable Restrictions");
        enableRestrictionsCheckBox.setToolTipText("Enable or disable playtime restrictions for your child.");

        startTimeField = new JTextField();
        startTimeField.setToolTipText("Enter the start time (e.g., 09:00 for 9 AM).");
        startTimeField.setBorder(BorderFactory.createTitledBorder("Start Time (HH:MM)"));

        endTimeField = new JTextField();
        endTimeField.setToolTipText("Enter the end time (e.g., 18:00 for 6 PM).");
        endTimeField.setBorder(BorderFactory.createTitledBorder("End Time (HH:MM)"));

        setRestrictionsButton = new JButton("Set Restrictions");

        restrictionsPanel.add(enableRestrictionsCheckBox);
        restrictionsPanel.add(new JLabel()); // Empty label for alignment
        restrictionsPanel.add(startTimeField);
        restrictionsPanel.add(endTimeField);
        restrictionsPanel.add(setRestrictionsButton);

        // Parental Statistics Section
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3, 1, 10, 10));
        statsPanel.setBorder(new TitledBorder("Statistics"));

        playtimeLabel = new JLabel("Total Playtime: " + totalPlayTime + " hours");
        avgSessionLabel = new JLabel("Average Session Time: " + (totalPlayTime / sessionCount) + " hours");
        resetStatsButton = new JButton("Reset Statistics");

        statsPanel.add(playtimeLabel);
        statsPanel.add(avgSessionLabel);
        statsPanel.add(resetStatsButton);

        // Revive Pet Section
        JPanel revivePanel = new JPanel();
        revivePanel.setBorder(new TitledBorder("Pet Revival"));
        revivePetButton = new JButton("Revive All Pets");
        revivePanel.add(revivePetButton);

        // Play Game as Parent Section
        JPanel playGamePanel = new JPanel();
        playGamePanel.setBorder(new TitledBorder("Play Game"));
        playGameButton = new JButton("Play Game as Parent");
        playGamePanel.add(playGameButton);

        // Add panels to the control panel
        controlPanel.add(restrictionsPanel);
        controlPanel.add(statsPanel);
        controlPanel.add(revivePanel);
        controlPanel.add(playGamePanel);

        // Action listeners for buttons
        setRestrictionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isEnabled = enableRestrictionsCheckBox.isSelected();
                String startTime = startTimeField.getText();
                String endTime = endTimeField.getText();
                // Implement logic to restrict playtime based on these values
                JOptionPane.showMessageDialog(frame, "Playtime restrictions " + (isEnabled ? "enabled" : "disabled") +
                        " from " + startTime + " to " + endTime + ".");
            }
        });

        resetStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalPlayTime = 0;
                sessionCount = 1;
                playtimeLabel.setText("Total Playtime: 0 hours");
                avgSessionLabel.setText("Average Session Time: 0 hours");
                JOptionPane.showMessageDialog(frame, "Statistics have been reset.");
            }
        });

        revivePetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reviveAllPets();
            }
        });

        playGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Launch game as parent
                launchGameAsParent();
            }
        });

        mainPanel.add(controlPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void reviveAllPets() {
        boolean anyRevived = false;
        for (Pet pet : pets) {
            if (pet.isDead) {
                pet.isDead = false;
                anyRevived = true;
            }
        }

        if (anyRevived) {
            JOptionPane.showMessageDialog(frame, "All dead pets have been revived!");
        } else {
            JOptionPane.showMessageDialog(frame, "No pets needed reviving.");
        }
    }

    private void launchGameAsParent() {
        // Here you would invoke the game's main play interface.
        // This is a placeholder implementation:
        JOptionPane.showMessageDialog(frame, "Game launched for parent!");
        // You might replace this with an actual game interface instantiation, e.g.,
        // new GameInterface(); // Assuming GameInterface is the main game class
    }

    // Inner class to represent a Pet
    class Pet {
        String name;
        boolean isDead;

        Pet(String name, boolean isDead) {
            this.name = name;
            this.isDead = isDead;
        }
    }
}
