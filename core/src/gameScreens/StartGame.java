package gameScreens;

import com.badlogic.gdx.Game;

/**
 * Created by pyc6eh4uk on 12.05.17.
 */

public class StartGame extends Game {
    @Override
    public void create() {
        System.out.println("Start game");
        this.setScreen(new MainMenuScreen(this));
    }
}
