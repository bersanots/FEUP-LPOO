package View;

import Model.MyComponent;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Swing extends Interface {

    private JFrame frame;
    private MyComponent gameWindow;
    private JLabel keysLabel;
    private JLabel keys_num;
    private JLabel livesLabel;
    private JLabel lives_num;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton newGameButton;
    private JButton endGameButton;
    private KeyStroke direction;

    public Swing() {
        this.frame = new JFrame("Castle Breakout");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //Game Window
        gameWindow = new MyComponent();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 10;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        frame.getContentPane().add(gameWindow, c);


        //Labels
        keysLabel = new JLabel("Keys");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 5, 5);
        c.gridx = 4;
        c.gridy = 0;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        frame.getContentPane().add(keysLabel, c);

        keys_num = new JLabel("0");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 5, 5, 10);
        c.gridx = 5;
        c.gridy = 0;
        frame.getContentPane().add(keys_num, c);

        livesLabel = new JLabel("Lives");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 10, 5);
        c.gridx = 4;
        c.gridy = 1;
        frame.getContentPane().add(livesLabel, c);

        lives_num = new JLabel("3");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 10, 10);
        c.gridx = 5;
        c.gridy = 1;
        frame.getContentPane().add(lives_num, c);


        //Buttons
        ButtonGroup button_group = new ButtonGroup();

        upButton = new JButton("Up");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 5, 5, 5);
        c.gridx = 2;
        c.gridy = 0;
        frame.getContentPane().add(upButton, c);

        downButton = new JButton("Down");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 10, 5);
        c.gridx = 2;
        c.gridy = 2;
        frame.getContentPane().add(downButton, c);

        leftButton = new JButton("Left");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 5, 5);
        c.gridx = 1;
        c.gridy = 1;
        frame.getContentPane().add(leftButton, c);

        rightButton = new JButton("Right");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 10);
        c.gridx = 3;
        c.gridy = 1;
        frame.getContentPane().add(rightButton, c);

        button_group.add(upButton);
        button_group.add(downButton);
        button_group.add(leftButton);
        button_group.add(rightButton);


        //Event Listeners
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                direction = new KeyStroke(KeyType.ArrowUp);
            }
        });

        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                direction = new KeyStroke(KeyType.ArrowDown);
            }
        });

        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                direction = new KeyStroke(KeyType.ArrowLeft);
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                direction = new KeyStroke(KeyType.ArrowRight);
            }
        });

        frame.getContentPane().addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                System.out.println(keyEvent.getKeyChar());
            }
        });

        KeyEventDispatcher keyEventDispatcher = new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 37:
                        direction = new KeyStroke(KeyType.ArrowLeft);
                        break;
                    case 38:
                        direction = new KeyStroke(KeyType.ArrowUp);
                        break;
                    case 39:
                        direction = new KeyStroke(KeyType.ArrowRight);
                        break;
                    case 40:
                        direction = new KeyStroke(KeyType.ArrowDown);
                        break;
                }
                return false;
            }
        };
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(keyEventDispatcher);

        frame.pack();
        frame.setVisible(true);
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
        lives_num.setText(String.valueOf(n));
    }

    public void setKeys(int n) {
        keys_num.setText(String.valueOf(n));
    }

}
