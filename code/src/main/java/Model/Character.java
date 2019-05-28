package Model;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.util.List;


public abstract class Character {
    Position pos;
    String img_path;

    public Character(int x, int y) {
        pos = new Position(x, y);
        img_path = "";
    }

    public void moveUp() {
        pos.setY(pos.getY() - 1);
    }

    public void moveDown() {
        pos.setY(pos.getY() + 1);
    }

    public void moveLeft() {
        pos.setX(pos.getX() - 1);
    }

    public void moveRight() {
        pos.setX(pos.getX() + 1);
    }


    public abstract void draw(TextGraphics graphics);

    public abstract void draw(MyComponent component, Graphics graphics);

    public Position getPos() {
        return pos;
    }

    public abstract boolean testCollisions(List<Wall> walls, KeyStroke k);

}
