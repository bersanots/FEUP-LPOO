import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Life extends Item{
    public Life(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        if (!isPicked_up()){
            graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(getPos().getX()*2, getPos().getY()), "L");
        }
    }

    @Override
    public void add_to_hero(Hero hero) {
        if (!isPicked_up()){
            hero.addLife();
        }
    }
}
