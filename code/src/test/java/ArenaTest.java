import Model.Arena;
import Model.Position;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArenaTest {

    private Arena arena;

    @Before
    public void startArena() {
        this.arena = new Arena(100, 25, 2);
    }

    @Test
    public void createElements() {
        assertNotNull(arena.getHero());
        assertNotNull(arena.getGate());
        assertNotNull(arena.getWalls());
        assertNotNull(arena.getMonsters());
        assertNotNull(arena.getWizards());
        assertNotNull(arena.getItems());
        assertNotEquals(arena.getWalls().size(), 0);
        assertNotEquals(arena.getMonsters().size(), 0);
        assertNotEquals(arena.getWizards().size(), 0);
        assertNotEquals(arena.getItems().size(), 0);
    }

    @Test
    public void heroMoves() {
        KeyStroke key = new KeyStroke(KeyType.ArrowRight);
        Position previous_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        arena.processKey(key, 0);
        Position curr_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        assertFalse(curr_pos.equals(previous_pos));

        key = new KeyStroke(KeyType.ArrowLeft);
        previous_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        arena.processKey(key, 0);
        curr_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        assertFalse(curr_pos.equals(previous_pos));

        key = new KeyStroke(KeyType.ArrowUp);
        previous_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        arena.processKey(key, 0);
        curr_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        assertFalse(curr_pos.equals(previous_pos));

        key = new KeyStroke(KeyType.ArrowDown);
        previous_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        arena.processKey(key, 0);
        curr_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        assertFalse(curr_pos.equals(previous_pos));
    }

    @Test
    public void heroDoesntMoveWithInvalidInput() {
        Position previous_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        arena.processKey(null, 0);
        Position curr_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        assertTrue(curr_pos.equals(previous_pos));
    }

    @Test
    public void heroDoesntMovePastWall() {
        KeyStroke key = new KeyStroke(KeyType.ArrowDown);
        Position previous_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        arena.processKey(key, 0);
        Position curr_pos = new Position(arena.getHero().getPos().getX(), arena.getHero().getPos().getY());
        assertTrue(curr_pos.equals(previous_pos));
    }

    @Test
    public void moveEnemies() {
        List<Position> monsters_previous_pos = new ArrayList<>();
        List<Position> wizards_previous_pos = new ArrayList<>();

        boolean one_monster_moves = false;
        boolean one_wizard_moves = false;

        for (int i = 0; i < arena.getMonsters().size(); i++)
            monsters_previous_pos.add(new Position(arena.getMonsters().get(i).getPos().getX(), arena.getMonsters().get(i).getPos().getY()));

        for (int i = 0; i < arena.getWizards().size(); i++)
            wizards_previous_pos.add(new Position(arena.getWizards().get(i).getPos().getX(), arena.getWizards().get(i).getPos().getY()));

        arena.processKey(null, 15);
        arena.processKey(null, 20);
        arena.processKey(null, 25);

        for (int i = 0; i < arena.getMonsters().size(); i++)
            if (!arena.getMonsters().get(i).getPos().equals(monsters_previous_pos.get(i)))
                one_monster_moves = true;

        assertTrue(one_monster_moves);

        arena.processKey(null, 40);

        for (int i = 0; i < arena.getWizards().size(); i++)
            if (!arena.getWizards().get(i).getPos().equals(wizards_previous_pos.get(i)))
                one_wizard_moves = true;

        assertTrue(one_wizard_moves);
    }

}
