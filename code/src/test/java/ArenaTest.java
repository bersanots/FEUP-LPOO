import Model.Arena;
import Model.Monster;
import Model.Position;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArenaTest {

    private Arena arena;

    @Before
    public void startArena() {
        this.arena = new Arena(100, 25);
    }

    @Test
    public void createElements() {
        assertNotNull(arena.getHero());
        assertNotNull(arena.getGate());
        assertNotNull(arena.getWalls());
        assertNotNull(arena.getMonsters());
        assertNotNull(arena.getWizards());
        assertNotNull(arena.getItems());
        assertNotEquals(arena.getWalls().size(),0);
        assertNotEquals(arena.getMonsters().size(),0);
        assertNotEquals(arena.getWizards().size(),0);
        assertNotEquals(arena.getItems().size(),0);
    }

    @Test
    public void heroMoves()  {
        KeyStroke key = new KeyStroke(KeyType.ArrowRight);
        Position previous_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        arena.processKey(key,0);
        Position curr_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        assertFalse(curr_pos.equals(previous_pos));

        key = new KeyStroke(KeyType.ArrowLeft);
        previous_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        arena.processKey(key,0);
        curr_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        assertFalse(curr_pos.equals(previous_pos));

        key = new KeyStroke(KeyType.ArrowUp);
        previous_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        arena.processKey(key,0);
        curr_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        assertFalse(curr_pos.equals(previous_pos));

        key = new KeyStroke(KeyType.ArrowDown);
        previous_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        arena.processKey(key,0);
        curr_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        assertFalse(curr_pos.equals(previous_pos));
    }

    @Test
    public void heroDoesntMoveWithInvalidInput()  {
        Position previous_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        arena.processKey(null,0);
        Position curr_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        assertTrue(curr_pos.equals(previous_pos));
    }

    @Test
    public void heroDoesntMovePastWall()  {
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);
        Position previous_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        arena.processKey(key,0);
        Position curr_pos = new Position(arena.getHero().getPos().getX(),arena.getHero().getPos().getY());
        assertTrue(curr_pos.equals(previous_pos));
    }

    @Test
    public void moveEnemies()  {
        Position monster_previous_pos = new Position(0,0);
        arena.processKey(null,0);
        for(Monster monster : arena.getMonsters())
            monster_previous_pos = arena.getHero().getPos();
        arena.processKey(null,1);
        assertTrue(arena.getHero().getPos().equals(monster_previous_pos));
    }

}
