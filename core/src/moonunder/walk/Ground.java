package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class Ground extends GameActor {
    public Ground(Vector position, Vector size) {
        super(new Box(position, size), new Vector(0, 0));

        this.sprite = new Sprite(new Texture(Gdx.files.internal("obstacles/bigRoof.png")));
        _static = true;
    }
}
