package moonunder.walk;


import com.badlogic.gdx.graphics.g2d.Animation;

/**
 * Created by pyc6eh4uk on 11.05.17.
 */

public class Enemy extends AnimatedActor {


    public Enemy(Vector position) {
        super(new Box(position, new Vector(Constants.ENEMY_WIDTH, Constants.ENEMY_HEIGHT)), new Vector(0, 0));
    }


}
