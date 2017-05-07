package moonunder.walk;

/**
 * Created by gdhsnlvr on 07.05.17.
 */
public class Collision {

    protected GameActor _actorA;
    protected GameActor _actorB;

    public Collision(GameActor actorA, GameActor actorB) {
        _actorA = actorA;
        _actorB = actorB;
    }

    public GameActor getActorA() {
        return _actorA;
    }

    public GameActor getActorB() {
        return _actorB;
    }
}