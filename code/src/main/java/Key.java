import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Key extends Item{

    public Key(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Screen screen) {
        if (!isPicked_up()){
            screen.setCharacter(getPos().getX()*2, getPos().getY(), new TextCharacter('K'));
        }
    }

    @Override
    public void add_to_hero(Hero hero) {
        if (!isPicked_up()){
            hero.addKey();
        }
    }
}
