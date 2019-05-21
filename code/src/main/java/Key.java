import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Key extends Item{

    public Key(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics) {
        if (!isPicked_up()){
            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(getPos().getX()*2, getPos().getY()), "K");
        }
    }

    @Override
    public void add_to_hero(Hero hero) {
        if (!isPicked_up()){
            hero.addKey();
        }
    }
}
