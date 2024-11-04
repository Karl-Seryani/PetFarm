package Game;

import UI.KeyboardListener;
import UI.MainScreen;

public class GameManager {
    public void start() {
        MainScreen mainscreen = new MainScreen();
        KeyboardListener keyboardListener = new KeyboardListener();

        mainscreen.addKeyListener(keyboardListener);
        mainscreen.setFocusable(true);
        mainscreen.requestFocus();
    }
}
