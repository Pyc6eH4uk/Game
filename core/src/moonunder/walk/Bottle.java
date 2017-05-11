package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by pyc6eh4uk on 11.05.17.
 */

public class Bottle extends Enemy {
    public Bottle(Vector position) {
        super(position);

        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("enemy/enemy_spritesheet.txt"));
        this.sprite = textureAtlas.createSprite("bottle");
    }

}
