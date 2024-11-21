package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainScreen extends JFrame {
    private JLabel imageLabel;
    private Image originalImage;

    public MainScreen() {
        // Load the initial image
        ImageIcon mainMenuImage = new ImageIcon("Assets/GameImages/MainMenu.png");
        originalImage = mainMenuImage.getImage();
        imageLabel = new JLabel(new ImageIcon(originalImage));

        MusicUtils.playBackgroundMusic("Assets/Sounds/MenuMusic.wav");

        // Set up the frame
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080); // Default resolution set to 1920x1080
        setLayout(null);

        // Set the bounds of the image label and add it to the frame
        imageLabel.setBounds(0, 0, 1920, 1080); // Cover the entire screen
        add(imageLabel);

        // Add a listener to handle window resizing
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int newWidth = getWidth();
                int newHeight = getHeight();

                // Scale the image to cover the entire screen
                Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));

                // Update the bounds of the imageLabel to match the new frame size
                imageLabel.setBounds(0, 0, newWidth, newHeight);

                // Refresh the frame
                revalidate();
                repaint();
            }
        });

        // Use a mouse listener to detect clicks
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                // Calculate scale factors based on the current size of the imageLabel
                double xScale = (double) imageLabel.getWidth() / 1920; // Adjust based on new resolution
                double yScale = (double) imageLabel.getHeight() / 1080;

                // Add sound effect path
                String clickSoundPath = "Assets/Sounds/click.wav";

                // Detect button clicks and play sound
                if (isWithinBounds(x, y, (int) (835 * xScale), (int) (620 * yScale), (int) (400 * xScale), (int) (120 * yScale))) {
                    ButtonUtils.playSound(clickSoundPath); // Play sound on "Game" button click
                    changeImage("Assets/GameImages/LoadGame.png", "Load Game Menu");
                } else if (isWithinBounds(x, y, (int) (835 * xScale), (int) (760 * yScale), (int) (400 * xScale), (int) (120 * yScale))) {
                    ButtonUtils.playSound(clickSoundPath); // Play sound on "Tutorial" button click
                    changeImage("Assets/GameImages/Tutorial.png", "Tutorial");
                } else if (isWithinBounds(x, y, (int) (835 * xScale), (int) (920 * yScale), (int) (400 * xScale), (int) (120 * yScale))) {
                    ButtonUtils.playSound(clickSoundPath); // Play sound on "Parental" button click
                    new ParentalControlsScreen(MainScreen.this);
                } else if (isWithinBounds(x, y, (int) (250 * xScale), (int) (450 * yScale), (int) (400 * xScale), (int) (200 * yScale))) {
                    ButtonUtils.playSound(clickSoundPath); // Play sound on "New Game" button click in Load Game Menu
                    changeImage("Assets/GameImages/PetSelection.png", "Pet Selection");
                } else if (isWithinBounds(x, y, (int) (1400 * xScale), (int) (450 * yScale), (int) (400 * xScale), (int) (200 * yScale))) {
                    ButtonUtils.playSound(clickSoundPath); // Play sound on "Go Back" button click in Load Game Menu
                    changeImage("Assets/GameImages/MainMenu.png", "Main Menu");
                }
            }
        });

        // Make the frame visible
        setVisible(true);
    }

    // Method to change the image and title
    private void changeImage(String imagePath, String newTitle) {
        ImageIcon newImageIcon = new ImageIcon(imagePath);
        originalImage = newImageIcon.getImage();

        int newWidth = getWidth();
        int newHeight = getHeight();

        // Scale the image to cover the entire screen
        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

        // Update the bounds of the imageLabel to match the new frame size
        imageLabel.setBounds(0, 0, newWidth, newHeight);

        setTitle(newTitle);

        revalidate();
        repaint();
    }

    private boolean isWithinBounds(int x, int y, int rectX, int rectY, int rectWidth, int rectHeight) {
        return x >= rectX && x <= rectX + rectWidth && y >= rectY && y <= rectY + rectHeight;
    }

    public static void main(String[] args) {
        new MainScreen();
    }
}
