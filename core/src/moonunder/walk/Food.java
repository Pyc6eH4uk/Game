package moonunder.walk;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class Food extends GameActor {

    public Food(Vector position) {
        super(new Box(position, new Vector(Constants.FOOD_WIDTH, Constants.FOOD_HEIGHT)), new Vector(0, 0));
    }

}
