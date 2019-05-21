import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.List;

public class Spell extends Character{
    int speed;
    KeyType key;

    public Spell(int x, int y, KeyType key, int speed) {
        super(x, y);
        this.speed = speed;
        this.key = key;
    }

    public int getSpeed(){return speed;}

    public void move(int time){
        if (time % speed == 0){
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
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#EE82EE"));
        graphics.putString(new TerminalPosition(pos.getX()*2, pos.getY()), "*");
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
