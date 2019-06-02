package View;

import Model.MyComponent;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import javax.swing.*;
import java.awt.*;

public abstract class Interface {

    private Screen screen;
    private JFrame frame;
    private MyComponent gameWindow;
    private JLabel keys_num;
    private JLabel lives_num;
    private KeyStroke direction;

    public Interface() {
    }

    public Screen getScreen() {
        return screen;
    }

    public Graphics getGraphics() {
        return frame.getContentPane().getGraphics();
    }

    public MyComponent getGameWindow() {
        return gameWindow;
    }

    public KeyStroke getDirection() {
        return direction;
    }

    public void resetDirection() {
        direction = null;
    }

    public void setLives(int n) {
    }

    public void setKeys(int n) {
    }

    public boolean isWindowClosed() {
        return false;
    }

    public void gameEndingMessage(boolean hasWon) {
    }
}
