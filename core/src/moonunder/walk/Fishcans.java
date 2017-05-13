package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by РусБЕНЧик on 12.05.2017.
 */

public class Fishcans extends Food {
    public Fishcans(Vector position) {
        super(position);

        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("food/foodandbonus.txt"));
        this.sprite = textureAtlas.createSprite("fishcans");
    }
}
