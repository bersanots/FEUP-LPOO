package Model;

import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;

public abstract class Item {
    private Position pos;
    private boolean picked_up;
    String img_path;

    public Item(int x , int y){
        pos = new Position(x, y);
        picked_up = false;
        img_path = "";
    }

    public Position getPos(){return pos;}

    public boolean isPicked_up() {
        return picked_up;
    }

    public void pick_up(){
        picked_up = true;
    }

    public abstract void draw(TextGraphics graphics);

    public abstract void draw(MyComponent component, Graphics graphics);

    public abstract void add_to_hero(Hero hero);
}
