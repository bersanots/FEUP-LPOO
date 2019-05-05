import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

public class Gate extends Wall{
    int n_keys;

    public Gate(int xi, int xf, int yi, int yf, int n_keys) {
        super(xi, xf, yi, yf);
        this.n_keys = n_keys;
    }

    public boolean openGate(Hero hero,KeyStroke key){
        if (hero.getKeys() < n_keys) return false;
        if(testCollisions(hero.getPos(), key)) {
            return false;
        }
        return true;
    }

    @Override
    public void draw(Screen screen) {
        int x = xi;
        int y = yi;

        while(x <= xf){
            while(y <= yf){
                screen.setCharacter(x*2, y, new TextCharacter('G'));
                y++;
            }
            y = yi;
            x++;
        }


    }


}
