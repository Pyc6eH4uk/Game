package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by pyc6eh4uk on 08.05.17.
 */

public class Background extends GameActor {

    public Background(Box box, Vector speed) {
        super(box, speed);
        this.sprite = new Sprite(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH)));
    }


}
