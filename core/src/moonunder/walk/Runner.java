package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class Runner extends AnimatedActor {

    public Runner(Vector position) {
        super(new Box(position, new Vector(Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT)), new Vector(0, 0));

        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal(Constants.BROWN_CAT_ATLAS_PATH));
        TextureRegion[] runningFrames = new TextureRegion[Constants.BROWN_CAT_RUNNING_REGION_NAMES.length];
        TextureRegion[] jumpingFrames = new TextureRegion[Constants.BROWN_CAT_JUMPING_REGION_NAMES.length];

        for (int i = 0; i < Constants.BROWN_CAT_RUNNING_REGION_NAMES.length; i++) {
            String path = Constants.BROWN_CAT_RUNNING_REGION_NAMES[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }

        runningAnimation = new Animation(0.1f, runningFrames);

        for (int j = 0; j < Constants.BROWN_CAT_JUMPING_REGION_NAMES.length; j++) {
            String path = Constants.BROWN_CAT_JUMPING_REGION_NAMES[j];
            jumpingFrames[j] = textureAtlas.findRegion(path);
        }

        jumpingAnimation = new Animation(0.1f, jumpingFrames);
    }

    @Override
    protected void land() {
        super.land();
        _speed.setX(-0.25f);
        _speed.setY(0);
    }
}
