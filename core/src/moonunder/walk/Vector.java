package moonunder.walk;

/**
 * Created by gdhsnlvr on 06.05.17.
 */

public class Vector {
    protected float _x;
    protected float _y;

    public Vector(float x, float y) {
        _x = x;
        _y = y;
    }

    public Vector add(Vector v) {
        return new Vector(_x + v._x, _y + v._y);
    }

    public Vector sub(Vector v) {
        return new Vector(_x - v._x, _y - v._y);
    }

    public Vector devide(float t) {
        return new Vector(_x / t, _y / t);
    }

    public Vector multiply(float t) {
        return new Vector(_x * t, _y * t);
    }

    public float length() {
        return (float) Math.sqrt(_x * _x + _y * _y);
    }

    public float getX() {
        return _x;
    }

    public float getY() {
        return _y;
    }

    public void setX(float _x) {
        this._x = _x;
    }

    public void setY(float _y) {
        this._y = _y;
    }
}