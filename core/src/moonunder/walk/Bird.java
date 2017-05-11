package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by pyc6eh4uk on 11.05.17.
 */

public class Bird extends Enemy {
    protected boolean _wasAttack;
    protected boolean _wasDowned;
    protected float _attackPosition;

    public Bird(Vector position, float attackPosition) {
        super(position);

        _wasAttack = false;
        _wasDowned = false;
        _attackPosition = attackPosition;

        TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal(Constants.FLYING_BIRD_PATH));
        TextureRegion[] flyingFrames = new TextureRegion[Constants.FLYING_BIRD_REGION_NAMES.length];

        for (int i = 0; i < Constants.FLYING_BIRD_REGION_NAMES.length; i++) {
            String path = Constants.FLYING_BIRD_REGION_NAMES[i];
            flyingFrames[i] = textureAtlas.findRegion(path);
        }

        runningAnimation = new Animation(0.1f, flyingFrames);
        birdBehaviour();
    }

    protected void birdBehaviour() {
        this._speed = new Vector(-2, 0);
    }

    public boolean isWasAttack() {
        return _wasAttack;
    }

    public boolean isWasDowned() {
        return _wasDowned;
    }

    public void setWasDowned(boolean _wasDowned) {
        this._wasDowned = _wasDowned;
    }

    public void setWasAttack(boolean _wasAttack) {
        this._wasAttack = _wasAttack;
    }

    public float getAttackPosition() {
        return _attackPosition;
    }
}
