package moonunder.walk;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by gdhsnlvr on 06.05.17.
 */
public class GameActor extends Actor {
    public static final float WORLD_TO_SCREEN = 64;
    protected Box _box;
    protected Vector _speed;
    protected Sprite sprite;
    protected boolean _static;
    protected boolean _jumping;
    protected boolean _passable;
    protected boolean _wasTouch;

    public GameActor(Box box, Vector speed) {
        _box = box;
        _speed = speed;
        _wasTouch = false;
        _static = false;
        _passable = false;
        this.sprite = null;
    }

    public boolean isWasTouch() {
        return _wasTouch;
    }

    public void setWasTouch(boolean _wasTouch) {
        this._wasTouch = _wasTouch;
    }

    public Box getBox() {
        return _box;
    }

    public Vector getSpeed() {
        return _speed;
    }

    public void setBox(Box _box) {
        this._box = _box;
    }

    public void setSpeed(Vector _speed) {
        this._speed = _speed;
    }

    protected static float transformToScreen(float n) {
        return n * WORLD_TO_SCREEN;
    }

    protected static float transformFromScreen(float n) {
        return n / WORLD_TO_SCREEN;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(sprite,
                transformToScreen(_box.getPosition()._x),
                transformToScreen(_box.getPosition()._y),
                transformToScreen(_box.getSize()._x),
                transformToScreen(_box.getSize()._y));
    }

    protected void land() {
        _jumping = false;
        _speed.setY(0);
    }

    protected void jump() {
        if (_jumping) {
            return;
        }
        _jumping = true;
        _speed.setX(2.5f);
        _speed.setY(3.5f);
    }
}

