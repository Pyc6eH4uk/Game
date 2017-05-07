package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class Trumpet extends Obstacle {

    public Trumpet(Vector position, Vector size) {
        super(position, size);

        this.sprite = new Sprite(new Texture(Gdx.files.internal("obstacles/trumpet.png")));
        _passable = false;
    }

}
