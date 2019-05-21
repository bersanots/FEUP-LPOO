import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.List;


public class Hero extends Character {
    private int lives;
    private int keys;
    private int invulnerable_time;

    public Hero(int x, int y) {
        super(x, y);
        lives = 3;
        invulnerable_time = 0;
        keys = 0;
    }

    public int getLives() {
        return lives;
    }

    public void addLife() {
        lives++;
    }

    public void takeLife() {
        lives--;
        invulnerable_time = 60;
    }

    public int getKeys() {
        return keys;
    }

    public void addKey() {
        keys++;
    }

    public int getInvulnerableTime() {
        return invulnerable_time;
    }

    @Override
    public void draw(TextGraphics graphics) {
        if (invulnerable_time == 0) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(pos.getX() * 2, pos.getY()), "H");
        } else {
            if (invulnerable_time > 50 || (invulnerable_time > 30 && invulnerable_time <= 40) || (invulnerable_time > 10 && invulnerable_time <= 20)){
                graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
                graphics.enableModifiers(SGR.BOLD);
                graphics.putString(new TerminalPosition(pos.getX() * 2, pos.getY()), "H");
            }
            invulnerable_time--;
        }

        int i = 0;
        while (i < lives) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
            graphics.putString(new TerminalPosition((32 + i) * 2, 15), "<");
            graphics.putString(new TerminalPosition((32 + i) * 2 + 1, 15), "3");
            i++;
        }

        i = 0;
        while (i < keys) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition((32 + i) * 2, 13), "K");
            i++;
        }
    }

    @Override
    public boolean testCollisions(List<Wall> walls, KeyStroke k) {
        int i = 0;
        while (i < walls.size()) {
            if (!walls.get(i).testCollisions(pos, k))
                return false;
            i++;
        }
        return true;
    }


}
