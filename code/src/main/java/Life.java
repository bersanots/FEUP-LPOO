import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Life extends Item{
    public Life(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Screen screen) {
        if (!isPicked_up()){
            screen.setCharacter(getPos().getX()*2, getPos().getY(), new TextCharacter('L'));
        }
    }

    @Override
    public void add_to_hero(Hero hero) {
        if (!isPicked_up()){
            hero.addLife();
        }
    }
}
