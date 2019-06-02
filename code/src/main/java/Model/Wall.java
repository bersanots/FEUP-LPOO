package Model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Wall {
    int xi;
    int xf;
    int yi;
    int yf;
    String img_path;

    public Wall(int xi, int xf, int yi, int yf){
        this.xf  = xf;
        this.yf  = yf;
        this.xi  = xi;
        this.yi  = yi;
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        img_path = s + "\\src\\main\\resources\\wall.jpg";
    }

    public void draw(TextGraphics graphics) {
        int x = xi;
        int y = yi;

        while(x <= xf){
            while(y <= yf){
                graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
                graphics.putString(new TerminalPosition(x*2, y), "X");
                y++;
            }
            y = yi;
            x++;
        }
    }

    public void draw(MyComponent component, Graphics graphics) {
        int x = xi;
        int y = yi;

        while(x <= xf){
            while(y <= yf){
                component.paintComponent(graphics, x*20, y*20, img_path);
                y++;
            }
            y = yi;
            x++;
        }
    }

    public boolean testCollisions(Position pos, KeyStroke key){
        switch (key.getKeyType()) {
            case ArrowUp:
                return pos.getX() < xi || pos.getX() > xf || (pos.getY() - 1 != yf);
            case ArrowRight:
                return pos.getY() < yi || pos.getY() > yf || (pos.getX() + 1 != xi);
            case ArrowDown:
                return pos.getX() < xi || pos.getX() > xf || (pos.getY() + 1 != yi);
            case ArrowLeft:
                return pos.getY() < yi || pos.getY() > yf || (pos.getX() - 1 != xf);
        }
        return false;
    }

    public boolean testPath(Position pos1, Position pos2) {
        if (pos1.equals(pos2)) return true;
        else if (pos1.getX() == pos2.getX()){
            if (pos1.getX()>= xi && pos1.getX()<= xf){
                if (pos1.getY() < pos2.getY() && pos1.getY() <= yi && pos2.getY() >= yf){
                    return false;
                }
                else if (pos1.getY() >= yi && pos2.getY() <= yf){
                    return false;
                }
            }
            return true;
        }
        else if (pos1.getY() == pos2.getY()){
            if (pos1.getY()>= yi && pos1.getY()<= yf){
                if (pos1.getX() < pos2.getX() && pos1.getX() <= xi && pos2.getX() >= xf){
                    return false;
                }
                else if (pos1.getX() >= xi && pos2.getX() <= xf){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
