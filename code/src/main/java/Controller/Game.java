package Controller;

import Model.Arena;
import View.*;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class Game {

    private int interface_op;
    private Interface interf;
    private Arena arena;

    public Game(int interface_op) throws IOException {
        this.interface_op = interface_op;
        if (interface_op == 1)
            this.interf = new Lanterna();
        else
            this.interf = new Swing();
        this.arena = new Arena(100, 25);
    }

    private void draw() throws IOException {
        if (interface_op == 1) {
            interf.getScreen().clear();
            arena.draw(interf.getScreen().newTextGraphics());
            interf.getScreen().refresh();
        } else {
            arena.draw(interf.getGameWindow(), interf.getGraphics());
        }
    }

    private void processKey(KeyStroke key, int i) {
        arena.processKey(key, i);
    }

    public void run() throws IOException, InterruptedException {
        if (interface_op == 1) {
            KeyStroke key;
            int i = 0;
            do {
                sleep(33);
                draw();
                key = interf.getScreen().pollInput();
                if (key != null)
                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                        interf.getScreen().close();
                        return;
                    }
                processKey(key, i);
                i++;
            } while ((key == null || key.getKeyType() != KeyType.EOF) && !arena.isGameOver());

            if (arena.hasWon()) {
                interf.getScreen().clear();
                interf.getScreen().setCharacter(10, 15, new TextCharacter('G'));
                interf.getScreen().setCharacter(11, 15, new TextCharacter('A'));
                interf.getScreen().setCharacter(12, 15, new TextCharacter('M'));
                interf.getScreen().setCharacter(13, 15, new TextCharacter('E'));
                interf.getScreen().setCharacter(14, 15, new TextCharacter(' '));
                interf.getScreen().setCharacter(15, 15, new TextCharacter('W'));
                interf.getScreen().setCharacter(16, 15, new TextCharacter('O'));
                interf.getScreen().setCharacter(17, 15, new TextCharacter('N'));
                interf.getScreen().refresh();
                sleep(1000);
            } else {
                interf.getScreen().clear();
                interf.getScreen().setCharacter(10, 15, new TextCharacter('Y'));
                interf.getScreen().setCharacter(11, 15, new TextCharacter('O'));
                interf.getScreen().setCharacter(12, 15, new TextCharacter('U'));
                interf.getScreen().setCharacter(13, 15, new TextCharacter(' '));
                interf.getScreen().setCharacter(14, 15, new TextCharacter('L'));
                interf.getScreen().setCharacter(15, 15, new TextCharacter('O'));
                interf.getScreen().setCharacter(16, 15, new TextCharacter('S'));
                interf.getScreen().setCharacter(17, 15, new TextCharacter('T'));
                interf.getScreen().refresh();
                sleep(1000);
            }

            interf.getScreen().close();

        } else {
            int i = 0;
            do {
                sleep(330);
                draw();
                processKey(interf.getDirection(), i);
                interf.resetDirection();
                i++;
            } while (!arena.isGameOver());
        }
    }
}
