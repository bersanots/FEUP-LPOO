package Model;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Gate extends Wall {
    int n_keys;
    private String floor_img_path;

    public Gate(int xi, int xf, int yi, int yf, int n_keys) {
        super(xi, xf, yi, yf);
        this.n_keys = n_keys;

        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        img_path = s + "\\src\\main\\resources\\gate.jpg";
        floor_img_path = s + "\\src\\main\\resources\\floor.jpg";
    }

    public boolean openGate(Hero hero, KeyStroke key) {
        if (hero.getKeys() < n_keys) return false;
        if (testCollisions(hero.getPos(), key)) {
            return false;
        }
        return true;
    }

    public void draw(TextGraphics graphics, Hero hero) {
        int x = xi;
        int y = yi;

        while (x <= xf) {
            while (y <= yf) {
                if (hero.getKeys() < n_keys) {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#8B4513"));
                    graphics.enableModifiers(SGR.BOLD);
                    graphics.putString(new TerminalPosition(x * 2, y), "G");
                } else
                    graphics.putString(new TerminalPosition(x * 2, y), " ");
                y++;
            }
            y = yi;
            x++;
        }
    }

    public void draw(MyComponent component, Graphics graphics, Hero hero) {
        int x = xi;
        int y = yi;

        while (x <= xf) {
            while (y <= yf) {
                if (hero.getKeys() < n_keys) {
                    component.paintComponent(graphics, x * 20, y * 20, img_path);
                } else
                    component.paintComponent(graphics, x * 20, y * 20, floor_img_path);
                y++;
            }
            y = yi;
            x++;
        }
    }


}
