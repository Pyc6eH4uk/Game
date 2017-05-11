package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by pyc6eh4uk on 11.05.17.
 */

public class Raccoon extends Enemy {
    public Raccoon(Vector position) {
        super(position);

        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal(Constants.RACCOON_ATLAS_PATH));
        TextureRegion[] runningFrames = new TextureRegion[Constants.RACCOON_REGION_NAMES.length];

        for (int i = 0; i < Constants.RACCOON_REGION_NAMES.length; i++) {
            String path = Constants.RACCOON_REGION_NAMES[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }

        runningAnimation = new Animation(0.33f, runningFrames);
        raccoonBehaviour();
    }

    protected void raccoonBehaviour() {
        this._speed = new Vector(-1, 0);
    }
}
