import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

public class Gate extends Wall {
    int n_keys;

    public Gate(int xi, int xf, int yi, int yf, int n_keys) {
        super(xi, xf, yi, yf);
        this.n_keys = n_keys;
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


}
