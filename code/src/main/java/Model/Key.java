package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;

public class Key extends Item{

    public Key(int x, int y) {
        super(x, y);
        img_path = "C:\\Users\\berna\\Documents\\GitHub\\projecto-lpoo-2019-lpoo_212\\code\\src\\main\\resources\\key.jpg";
    }

    public void draw(TextGraphics graphics) {
        if (!isPicked_up()){
            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(getPos().getX()*2, getPos().getY()), "K");
        }
    }

    @Override
    public void draw(MyComponent component, Graphics graphics) {
        if (!isPicked_up()) {
            component.paintComponent(graphics, getPos().getX() * 20, getPos().getY() * 20, img_path);
        }
    }

    @Override
    public void add_to_hero(Hero hero) {
        if (!isPicked_up()){
            hero.addKey();
        }
    }
}
