package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class AnimatedActor extends GameActor {
    protected float stateTime;
    protected Animation runningAnimation;
    protected Animation jumpingAnimation;

    public AnimatedActor(Box box, Vector speed) {
        super(box, speed);

        stateTime = 0.0f;
        _jumping = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();

        if (_jumping) {
            batch.draw((TextureRegion)jumpingAnimation.getKeyFrame(stateTime, true),
                    transformToScreen(_box.getPosition()._x),
                    transformToScreen(_box.getPosition()._y),
                    transformToScreen(_box.getSize()._x), transformToScreen(_box.getSize()._y));
        } else {
            batch.draw((TextureRegion)runningAnimation.getKeyFrame(stateTime, true),
                    transformToScreen(_box.getPosition()._x),
                    transformToScreen(_box.getPosition()._y),
                    transformToScreen(_box.getSize()._x), transformToScreen(_box.getSize()._y));
        }
    }
}
