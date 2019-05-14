import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import java.util.List;

import static com.googlecode.lanterna.input.KeyType.*;


public class Monster extends Character {
    KeyType key;
    int speed;

    public Monster(int x, int y, boolean vertical, int speed) {
        super(x, y);
        if (vertical)
            key = ArrowDown;
        else
            key = ArrowRight;
        this.speed = speed;
    }

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
        screen.setCharacter(pos.getX()*2, pos.getY(), new TextCharacter('M'));
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
