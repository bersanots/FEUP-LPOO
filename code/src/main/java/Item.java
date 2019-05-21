import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Item {
    private Position pos;
    private boolean picked_up;
    public Item(int x , int y){
        pos = new Position(x, y);
        picked_up = false;
    }

    public Position getPos(){return pos;}

    public boolean isPicked_up() {
        return picked_up;
    }

    public void pick_up(){
        picked_up = true;
    }

    public abstract void draw(TextGraphics graphics);
    public abstract void add_to_hero(Hero hero);
}
