import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Game {

    private Screen screen;
    private Hero hero;
    private Gate gate;
    private List<Wall> walls;
    private List<Monster> monsters;
    private List<Wizard> wizards;
    private List<Item> items;

    public Game() throws IOException {
        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        hero = new Hero(28, 19);
        gate = new Gate(2,4,20,20,3);
        walls = new ArrayList<>();
        monsters =  new ArrayList<>();
        wizards = new ArrayList<>();
        items = new ArrayList<>();
        createWalls();
        createMonsters();
        createWizards();
        createItems();
    }
    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                if(hero.testCollisions(walls, key))
                    hero.moveUp();
                break;
            case ArrowRight:
                if(hero.testCollisions(walls, key))
                    hero.moveRight();
                break;
            case ArrowDown:
                if(hero.testCollisions(walls, key))
                    hero.moveDown();
                break;
            case ArrowLeft:
                if(hero.testCollisions(walls, key))
                    hero.moveLeft();
                break;
        }
        System.out.println(key);
    }

    public void run() throws IOException, InterruptedException {
        KeyStroke key;
        int i = 0;
        do {
            sleep(33);
            screen.clear();
            drawWalls();
            gate.draw(screen);
            drawItems();
            moveWizards(i);
            drawWizards();
            moveMonsters(i);
            drawMonsters();
            hero.draw(screen);
            screen.refresh();
            checkItems();
            if(hero.getInvulnerableTime() == 0){
                if (testKill()) {
                    hero.takeLife();
                    if (hero.getLives() == 0){
                        screen.close();
                        break;
                    }
                }
            }

            key = screen.pollInput();
            if (key == null) {
            } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')
                screen.close();
            else {

                if (gate.openGate(hero, key)){
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
                    screen.close();
                    break;
                }
                processKey(key);
            }
            i++;
        } while (key == null || key.getKeyType() != KeyType.EOF);
    }

    public void createWalls(){
        Wall wall_1 = new Wall(1,1,1, 20);
        walls.add(wall_1);
        Wall wall_2 = new Wall(30,30,1, 20);
        walls.add(wall_2);
        Wall wall_3 = new Wall(2,29,1, 1);
        walls.add(wall_3);
        Wall wall_4 = new Wall(2,29,20, 20);
        walls.add(wall_4);
        Wall wall_5 = new Wall(5,5,10, 19);
        walls.add(wall_5);
        Wall wall_6 = new Wall(2,12,7, 7);
        walls.add(wall_6);
        Wall wall_7 = new Wall(26,26,5, 19);
        walls.add(wall_7);
        Wall wall_8 = new Wall(15,15,2, 17);
        walls.add(wall_8);
        Wall wall_9 = new Wall(23,25,17, 17);
        walls.add(wall_9);
        Wall wall_10 = new Wall(23,23,15, 16);
        walls.add(wall_10);
        Wall wall_11 = new Wall(22,22,7, 12);
        walls.add(wall_11);
        Wall wall_12 = new Wall(6,12,4, 4);
        walls.add(wall_12);
        Wall wall_13 = new Wall(12,12,5, 6);
        walls.add(wall_13);
        Wall wall_14 = new Wall(3,5,9, 9);
        walls.add(wall_14);
        Wall wall_15 = new Wall(9,9,8, 8);
        walls.add(wall_15);
        Wall wall_16 = new Wall(7,9,9, 9);
        walls.add(wall_16);
        Wall wall_17 = new Wall(8,14,17, 17);
        walls.add(wall_17);
        Wall wall_18 = new Wall(12,12,8, 14);
        walls.add(wall_18);
        Wall wall_19 = new Wall(8,11,14, 14);
        walls.add(wall_19);
        Wall wall_20 = new Wall(7,8,10, 10);
        walls.add(wall_20);
        Wall wall_21 = new Wall(6,10,12, 12);
        walls.add(wall_21);
        Wall wall_22 = new Wall(10,10,9, 9);
        walls.add(wall_22);
        Wall wall_23 = new Wall(10,10,11, 11);
        walls.add(wall_23);
    }

    public void createMonsters(){
        Monster monster_1 = new Monster(27,10, false, 20);
        monsters.add(monster_1);
        Monster monster_2 = new Monster(28,12, false,25);
        monsters.add(monster_2);
        Monster monster_3 = new Monster(29,8, false,15);
        monsters.add(monster_3);
        Monster monster_4 = new Monster(5,18, false,15);
        monsters.add(monster_4);
        Monster monster_5 = new Monster(25,18, false,15);
        monsters.add(monster_5);
        Monster monster_6 = new Monster(15,19, false,5);
        monsters.add(monster_6);
        Monster monster_7 = new Monster(13,6, false,50);
        monsters.add(monster_7);
        Monster monster_8 = new Monster(10,2, true,50);
        monsters.add(monster_8);
        Monster monster_9 = new Monster(10,4, true,50);
        monsters.add(monster_9);
        Monster monster_10 = new Monster(2,8, false,5);
        monsters.add(monster_10);

    }

    public void createWizards(){
        Wizard wizard_1 = new Wizard(15,15,false,40,5);
        wizards.add(wizard_1);
        Wizard wizard_2 = new Wizard(18,8,false,35,5);
        wizards.add(wizard_2);
        Wizard wizard_3 = new Wizard(20,11,false,30,5);
        wizards.add(wizard_3);
        Wizard wizard_4 = new Wizard(23,8,false,35,5);
        wizards.add(wizard_4);
        Wizard wizard_5 = new Wizard(25,11,false,30,5);
        wizards.add(wizard_5);
        Wizard wizard_6 = new Wizard(2,4,true,30,5);
        wizards.add(wizard_6);
        Wizard wizard_7 = new Wizard(10,8,false,50,5);
        wizards.add(wizard_7);

    }

    public void createItems(){
        Item item_1 = new Life(25,16);
        items.add(item_1);
        Item item_2 = new Key(29,2);
        items.add(item_2);
        Item item_3 = new Key(25,19);
        items.add(item_3);
        Item item_4 = new Key(11,6);
        items.add(item_4);
    }

    public void drawWalls() {
        int i = 0;
        while (i < walls.size()){
            walls.get(i).draw(screen);
            i++;
        }
    }

    public void drawItems(){
        int i = 0;
        while (i < items.size()){
            items.get(i).draw(screen);
            i++;
        }
    }

    public void drawMonsters(){
        int i = 0;
        while (i < monsters.size()){
            monsters.get(i).draw(screen);
            i++;
        }
    }

    public void drawWizards(){
        int i = 0;
        while (i < wizards.size()){
            wizards.get(i).draw(screen);
            i++;
        }
    }

    public void moveMonsters(int i){
        int it = 0;
        while (it < monsters.size()){
            if (monsters.get(it).testCollisions(walls, new KeyStroke(KeyType.ArrowRight)))
                monsters.get(it).move(i);
            else
                monsters.get(it).turnAround();
            it++;
        }
    }

    public void moveWizards(int i){
        int it = 0;
        while (it < wizards.size()){
            if (wizards.get(it).testCollisions(walls, new KeyStroke(KeyType.ArrowRight)))
                wizards.get(it).move(i);
            else
                wizards.get(it).turnAround();

            wizards.get(it).moveSpell(i, walls);
            wizards.get(it).newSpell(walls,hero);
            it++;
        }
    }

    public void checkItems(){
        int i = 0;
        while (i < items.size()){
            if (items.get(i).getPos().equals(hero.getPos())){
                items.get(i).add_to_hero(hero);
                items.get(i).pick_up();
            }
            i++;
        }
    }

    public boolean testKill(){
        int i = 0;
        while (i < monsters.size()){
            if (monsters.get(i).getPos().equals(hero.getPos()))
                return true;
            i++;
        }
        i=0;
        while (i < wizards.size()){
            if (wizards.get(i).getSpell() != null){
                if (wizards.get(i).getSpell().getPos().equals(hero.getPos())){
                    wizards.get(i).eraseSpell(0);
                    return true;
                }
            }

            i++;
        }
        return false;
    }


}
