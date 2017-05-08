package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class Milk extends Food {
    public Milk(Vector position) {
        super(position);

        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("food/products.txt"));
        this.sprite = textureAtlas.createSprite("2");
    }
}
