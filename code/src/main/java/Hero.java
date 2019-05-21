import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.util.List;


public class Hero extends Character {
    private int lives;
    private int keys;
    private int invulnerable_time;
    public Hero(int x, int y) {
        super(x, y);
        lives = 3;
        invulnerable_time = 0;
        keys=0;
    }

    public int getLives(){return lives;}
    public void addLife(){lives++;}
    public void takeLife(){
        lives--;
        invulnerable_time = 60;
    }

    public int getKeys(){return keys;}
    public void addKey(){keys++;}
    public int getInvulnerableTime(){return invulnerable_time;}

    @Override
    public void draw(Screen screen) {
        if (invulnerable_time == 0)
            screen.setCharacter(pos.getX()*2, pos.getY(), new TextCharacter('H'));
        else{
            if (invulnerable_time > 50 || (invulnerable_time > 30 && invulnerable_time <= 40) || (invulnerable_time > 10 && invulnerable_time <= 20))
                screen.setCharacter(pos.getX()*2, pos.getY(), new TextCharacter('H'));
            invulnerable_time--;
        }

        int i = 0;
        while (i<lives){
            screen.setCharacter((32+i)*2, 15, new TextCharacter('<'));
            screen.setCharacter((32+i)*2 + 1, 15, new TextCharacter('3'));
            i++;
        }

        i = 0;
        while (i<keys){
            screen.setCharacter((32+i)*2, 13, new TextCharacter('K'));
            i++;
        }
    }

    @Override
    public boolean testCollisions(List<Wall> walls, KeyStroke k) {
        int i = 0;
        while (i < walls.size()){
            if (!walls.get(i).testCollisions(pos, k))
                return false;
            i++;
        }
        return true;
    }


}
