import Controller.Game;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class GameTest {

    @Test
    public void startTextBasedGame() throws IOException, InterruptedException {
        Game game = new Game(1);
        game.run();
        assertFalse(game.getArena().isGameOver());
    }

    @Test
    public void startGraphicBasedGame() throws IOException, InterruptedException {
        Game game = new Game(2);
        game.run();
        assertFalse(game.getArena().isGameOver());
    }

}
