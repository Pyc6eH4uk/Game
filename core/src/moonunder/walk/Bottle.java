package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by pyc6eh4uk on 11.05.17.
 */

public class Bottle extends Enemy {
    private Sprite sprite;
    public Bottle(Vector position) {
        super(position);
        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("enemy/enemy_spritesheet.txt"));
        sprite = textureAtlas.createSprite("bottle");
        this._speed = new Vector(0, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite,
                transformToScreen(_box.getPosition()._x),
                transformToScreen(_box.getPosition()._y),
                transformToScreen(_box.getSize()._x),
                transformToScreen(_box.getSize()._y));
    }
}
