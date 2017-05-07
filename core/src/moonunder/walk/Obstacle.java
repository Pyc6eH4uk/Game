package moonunder.walk;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class Obstacle extends GameActor {

    public Obstacle(Vector position, Vector size) {
        super(new Box(position, size), new Vector(0, 0));

        _static = true;
        _passable = true;
    }
}
