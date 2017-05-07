package moonunder.walk;

/**
 * Created by gdhsnlvr on 06.05.17.
 */
public class Box {
    protected Vector _position;
    protected Vector _size;

    public Box(Vector position, Vector size) {
        _position = position;
        _size = size;
    }

    public Vector getPosition() {
        return _position;
    }

    public Vector getSize() {
        return _size;
    }

    public void setPosition(Vector _position) {
        this._position = _position;
    }

    public void setSize(Vector _size) {
        this._size = _size;
    }

    public Box move(Vector vector) {
        return new Box(_position.add(vector), _size);
    }

    static public boolean overlaps(Box a, Box b) {
        if (a._position.getX() + a._size.getX() < b._position.getX() ||
                 a._position.getX() > b._position.getX() + b._size.getX() ||
                 a._position.getY() > b._position.getY() + b._size.getY() ||
                 a._position.getY() + a._size.getY() < b._position.getY()) return  false;
        return true;
    }


}