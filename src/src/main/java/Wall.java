import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;


import com.googlecode.lanterna.input.KeyStroke;

public class Wall {
    int xi;
    int xf;
    int yi;
    int yf;

    public Wall(int xi, int xf, int yi, int yf){
        this.xf  = xf;
        this.yf  = yf;
        this.xi  = xi;
        this.yi  = yi;
    }

    public void draw(Screen screen) {
        int x = xi;
        int y = yi;

        while(x <= xf){
            while(y <= yf){
                screen.setCharacter(x*2, y, new TextCharacter('X'));
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
