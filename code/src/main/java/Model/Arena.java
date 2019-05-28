package Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Arena {

    private int width;
    private int height;
    private Hero hero = new Hero(28, 19);
    private Gate gate = new Gate(2, 4, 20, 20, 3);
    private List<Wall> walls;
    private List<Monster> monsters;
    private List<Wizard> wizards;
    private List<Item> items;
    private State game_state = State.RUNNING;

    enum State {
        WON,
        LOST,
        RUNNING
    }

    public Arena(int w, int h) {
        this.width = w;
        this.height = h;
        walls = new ArrayList<>();
        monsters = new ArrayList<>();
        wizards = new ArrayList<>();
        items = new ArrayList<>();
        createWalls();
        createMonsters();
        createWizards();
        createItems();
    }

    public void processKey(KeyStroke key, int i) {
        if (key != null) {
            switch (key.getKeyType()) {
                case ArrowUp:
                    if (hero.testCollisions(walls, key))
                        hero.moveUp();
                    break;
                case ArrowRight:
                    if (hero.testCollisions(walls, key))
                        hero.moveRight();
                    break;
                case ArrowDown:
                    if (hero.testCollisions(walls, key))
                        hero.moveDown();
                    break;
                case ArrowLeft:
                    if (hero.testCollisions(walls, key))
                        hero.moveLeft();
                    break;
            }
            System.out.println(key);
        }
        runArena(key, i);
    }

    private void runArena(KeyStroke key, int i) {
        moveWizards(i);
        moveMonsters(i);
        checkItems();
        if (hero.getInvulnerableTime() == 0 && testKill()) {
            hero.takeLife();
            if (hero.getLives() == 0) {
                game_state = State.LOST;
                return;
            }
        }
        if (key != null && gate.openGate(hero, key)) {
            game_state = State.WON;
        }
    }

    public boolean isGameOver() {
        return game_state != State.RUNNING;
    }

    public boolean hasWon() {
        return game_state == State.WON;
    }

    public void createWalls() {
        Wall wall_1 = new Wall(1, 1, 1, 20);
        walls.add(wall_1);
        Wall wall_2 = new Wall(30, 30, 1, 20);
        walls.add(wall_2);
        Wall wall_3 = new Wall(2, 29, 1, 1);
        walls.add(wall_3);
        Wall wall_4 = new Wall(2, 29, 20, 20);
        walls.add(wall_4);
        Wall wall_5 = new Wall(5, 5, 10, 19);
        walls.add(wall_5);
        Wall wall_6 = new Wall(2, 12, 7, 7);
        walls.add(wall_6);
        Wall wall_7 = new Wall(26, 26, 5, 19);
        walls.add(wall_7);
        Wall wall_8 = new Wall(15, 15, 2, 17);
        walls.add(wall_8);
        Wall wall_9 = new Wall(23, 25, 17, 17);
        walls.add(wall_9);
        Wall wall_10 = new Wall(23, 23, 15, 16);
        walls.add(wall_10);
        Wall wall_11 = new Wall(22, 22, 7, 12);
        walls.add(wall_11);
        Wall wall_12 = new Wall(6, 12, 4, 4);
        walls.add(wall_12);
        Wall wall_13 = new Wall(12, 12, 5, 6);
        walls.add(wall_13);
        Wall wall_14 = new Wall(3, 5, 9, 9);
        walls.add(wall_14);
        Wall wall_15 = new Wall(9, 9, 8, 8);
        walls.add(wall_15);
        Wall wall_16 = new Wall(7, 9, 9, 9);
        walls.add(wall_16);
        Wall wall_17 = new Wall(8, 14, 17, 17);
        walls.add(wall_17);
        Wall wall_18 = new Wall(12, 12, 8, 14);
        walls.add(wall_18);
        Wall wall_19 = new Wall(8, 11, 14, 14);
        walls.add(wall_19);
        Wall wall_20 = new Wall(7, 8, 10, 10);
        walls.add(wall_20);
        Wall wall_21 = new Wall(6, 10, 12, 12);
        walls.add(wall_21);
        Wall wall_22 = new Wall(10, 10, 9, 9);
        walls.add(wall_22);
        Wall wall_23 = new Wall(10, 10, 11, 11);
        walls.add(wall_23);
    }

    public void createMonsters() {
        Monster monster_1 = new Monster(27, 10, false, 20);
        monsters.add(monster_1);
        Monster monster_2 = new Monster(28, 12, false, 25);
        monsters.add(monster_2);
        Monster monster_3 = new Monster(29, 8, false, 15);
        monsters.add(monster_3);
        Monster monster_4 = new Monster(5, 18, false, 15);
        monsters.add(monster_4);
        Monster monster_5 = new Monster(25, 18, false, 15);
        monsters.add(monster_5);
        Monster monster_6 = new Monster(15, 19, false, 5);
        monsters.add(monster_6);
        Monster monster_7 = new Monster(13, 6, false, 50);
        monsters.add(monster_7);
        Monster monster_8 = new Monster(10, 2, true, 50);
        monsters.add(monster_8);
        Monster monster_9 = new Monster(10, 4, true, 50);
        monsters.add(monster_9);
        Monster monster_10 = new Monster(2, 8, false, 5);
        monsters.add(monster_10);
    }

    public void createWizards() {
        Wizard wizard_1 = new Wizard(15, 15, false, 40, 5);
        wizards.add(wizard_1);
        Wizard wizard_2 = new Wizard(18, 8, false, 35, 5);
        wizards.add(wizard_2);
        Wizard wizard_3 = new Wizard(20, 11, false, 30, 5);
        wizards.add(wizard_3);
        Wizard wizard_4 = new Wizard(23, 8, false, 35, 5);
        wizards.add(wizard_4);
        Wizard wizard_5 = new Wizard(25, 11, false, 30, 5);
        wizards.add(wizard_5);
        Wizard wizard_6 = new Wizard(2, 4, true, 30, 5);
        wizards.add(wizard_6);
        Wizard wizard_7 = new Wizard(10, 8, false, 50, 5);
        wizards.add(wizard_7);
    }

    public void createItems() {
        Item item_1 = new Life(25, 16);
        items.add(item_1);
        Item item_2 = new Key(29, 2);
        items.add(item_2);
        Item item_3 = new Key(25, 19);
        items.add(item_3);
        Item item_4 = new Key(11, 6);
        items.add(item_4);
    }

    public void moveMonsters(int i) {
        int it = 0;
        while (it < monsters.size()) {
            if (monsters.get(it).testCollisions(walls, new KeyStroke(KeyType.ArrowRight)))
                monsters.get(it).move(i);
            else
                monsters.get(it).turnAround();
            it++;
        }
    }

    public void moveWizards(int i) {
        int it = 0;
        while (it < wizards.size()) {
            if (wizards.get(it).testCollisions(walls, new KeyStroke(KeyType.ArrowRight)))
                wizards.get(it).move(i);
            else
                wizards.get(it).turnAround();

            wizards.get(it).moveSpell(i, walls);
            wizards.get(it).newSpell(walls, hero);
            it++;
        }
    }

    public void checkItems() {
        int i = 0;
        while (i < items.size()) {
            if (items.get(i).getPos().equals(hero.getPos())) {
                items.get(i).add_to_hero(hero);
                items.get(i).pick_up();
            }
            i++;
        }
    }

    public boolean testKill() {
        int i = 0;
        while (i < monsters.size()) {
            if (monsters.get(i).getPos().equals(hero.getPos()))
                return true;
            i++;
        }
        i = 0;
        while (i < wizards.size()) {
            if (wizards.get(i).getSpell() != null) {
                if (wizards.get(i).getSpell().getPos().equals(hero.getPos())) {
                    wizards.get(i).eraseSpell(0);
                    return true;
                }
            }

            i++;
        }
        return false;
    }

    public Hero getHero() {
        return hero;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        gate.draw(graphics, hero);
        for (Item item : items)
            item.draw(graphics);
        for (Wizard wizard : wizards)
            wizard.draw(graphics);
        for (Monster monster : monsters)
            monster.draw(graphics);
        hero.draw(graphics);
    }

    public void draw(MyComponent component, Graphics graphics) {
        for (int x = 1; x <= 30; x++)
            for (int y = 1; y <= 20; y++)
                component.paintComponent(graphics, x * 20, y * 20, "C:\\Users\\berna\\Documents\\GitHub\\projecto-lpoo-2019-lpoo_212\\code\\src\\main\\resources\\floor.jpg");
        for (Wall wall : walls)
            wall.draw(component, graphics);
        gate.draw(component, graphics, hero);
        for (Item item : items)
            item.draw(component, graphics);
        for (Wizard wizard : wizards)
            wizard.draw(component, graphics);
        for (Monster monster : monsters)
            monster.draw(component, graphics);
        hero.draw(component, graphics);
    }
}
