import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class Game {

    private Screen screen;
    private Arena arena;

    public Game() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        this.arena = new Arena(100, 25);
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key, int i) {
        arena.processKey(key, i);
    }

    public void run() throws IOException, InterruptedException {
        KeyStroke key;
        int i = 0;
        do {
            sleep(33);
            draw();
            key = screen.pollInput();
            if (key != null)
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                    screen.close();
            processKey(key, i);
            i++;
        } while ((key == null || key.getKeyType() != KeyType.EOF) && !arena.isGameOver());

        if (arena.hasWon()) {
            screen.clear();
            screen.setCharacter(10, 15, new TextCharacter('G'));
            screen.setCharacter(11, 15, new TextCharacter('A'));
            screen.setCharacter(12, 15, new TextCharacter('M'));
            screen.setCharacter(13, 15, new TextCharacter('E'));
            screen.setCharacter(14, 15, new TextCharacter(' '));
            screen.setCharacter(15, 15, new TextCharacter('W'));
            screen.setCharacter(16, 15, new TextCharacter('O'));
            screen.setCharacter(17, 15, new TextCharacter('N'));
            screen.refresh();
            sleep(1000);
        }

        screen.close();
    }
}
