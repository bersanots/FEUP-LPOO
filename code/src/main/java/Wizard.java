import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.util.List;

import static com.googlecode.lanterna.input.KeyType.*;


public class Wizard extends Character {
    KeyType key;
    int speed;
    int spell_speed;
    Spell spell;
    boolean spellFired;
    int spell_cooldown;

    public Wizard(int x, int y, boolean vertical, int speed, int spell_speed) {
        super(x, y);
        if (vertical)
            key = ArrowDown;
        else
            key = ArrowRight;
        this.speed = speed;

        spell = null;

        spellFired = false;

        this.spell_speed = spell_speed;

        spell_cooldown = 0;
    }

    public Spell getSpell(){return spell;}

    public void move(int time){
        if (time % speed != 0)
            return;
        switch (key) {
            case ArrowUp:
                moveUp();
                break;
            case ArrowRight:
                moveRight();
                break;
            case ArrowDown:
                moveDown();
                break;
            case ArrowLeft:
                moveLeft();
                break;
        }
    }

    public void moveSpell(int time,List<Wall> walls) {
        if (spellFired){
            if (spell.testCollisions(walls,new KeyStroke(KeyType.ArrowRight))){
                spell.move(time);
            }
            else eraseSpell();
        }
    }

    public void eraseSpell(){
        spell = null;
        spellFired = false;
    }

    public void newSpell(List<Wall> walls, Hero hero){
        if (spell_cooldown > 0){
            spell_cooldown--;
            return;
        }
        if (spellFired) return;
        if (test_spell_path(walls, hero)){
            spellFired = true;
            spell_cooldown = 60;
            if(pos.getX() == hero.getPos().getX()){
                if (pos.getY() < hero.getPos().getY())
                    spell = new Spell(pos.getX(),pos.getY(),ArrowDown,spell_speed);
                else
                    spell = new Spell(pos.getX(),pos.getY(),ArrowUp,spell_speed);
            }
            else{
                if (pos.getX() < hero.getPos().getX())
                    spell = new Spell(pos.getX(),pos.getY(),ArrowRight,spell_speed);
                else
                    spell = new Spell(pos.getX(),pos.getY(),ArrowLeft,spell_speed);
            }

        }

    }

    public boolean test_spell_path(List<Wall> walls, Hero hero){
        int i = 0;
        while (i < walls.size()){
            if (!walls.get(i).testPath(pos, hero.getPos()))
                return false;
            i++;
        }
        return true;

    }



    public void turnAround() {
        switch (key) {
            case ArrowUp:
                key = ArrowDown;
                break;
            case ArrowRight:
                key = ArrowLeft;
                break;
            case ArrowDown:
                key = ArrowUp;
                break;
            case ArrowLeft:
                key = ArrowRight;
                break;
        }
    }


    @Override
    public void draw(Screen screen) {
        if (spellFired)
            spell.draw(screen);
        screen.setCharacter(pos.getX()*2, pos.getY(), new TextCharacter('W'));
    }

    @Override
    public boolean testCollisions(List<Wall> walls, KeyStroke k) {
        k = new KeyStroke(key);
        int i = 0;
        while (i < walls.size()){
            if (!walls.get(i).testCollisions(pos, k))
                return false;
            i++;
        }
        return true;
    }

}
