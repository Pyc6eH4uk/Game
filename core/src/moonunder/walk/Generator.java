package moonunder.walk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;

import java.util.ArrayList;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class Generator {

    protected static final float MINIMAL_SPACE_BETWEEN_GROUND = 0.9f * Constants.RUNNER_WIDTH;
    protected static final float MAXIMAL_SPACE_BETWEEN_GROUND_WITHOUT_MINIMAL_SPACE = 1.0f * Constants.RUNNER_WIDTH;
    protected static final float MINIMAL_GROUD_SIZE = 6.0f * Constants.RUNNER_WIDTH;
    protected static final float MAXIMAL_GROUD_SIZE_WITHOUT_MINIMAL = 4.0f * Constants.RUNNER_WIDTH;
    protected static final float MINIMAL_SPACE = 1.0f * Constants.RUNNER_WIDTH;
    protected static final float MAXIMAL_SPACE_WITHOUT_MINIMAL = 1.5f * Constants.RUNNER_WIDTH;

    protected static final float MIN_SPACE_BETWEEN_FOOD = 1.0f * Constants.FOOD_WIDTH;

    protected GameActor lastGeneratedFood;
    protected GameActor lastGeneratedGround;
    protected GameActor lastGeneratedEnemy;
    protected GameActor lastGeneratedPassableObstacle;
    protected GameActor lastGeneratedImpassableObstacle;

    protected Camera camera;

    public Generator(Camera camera) {
        this.camera = camera;
    }

    public GameActor generateGround() {
        float cameraRightPosition = GameActor.transformFromScreen(camera.position.x + camera.viewportWidth);
        if (lastGeneratedGround != null && getRightPosition(lastGeneratedGround) > cameraRightPosition)
            return null;

        lastGeneratedGround = new Ground(new Vector(generateGroundPosition(), 0), new Vector(generateGroundSize(), (float) Math.random() * 2.0f + 0.5f));
        return lastGeneratedGround;
    }

    protected float generateGroundPosition() {
        if (lastGeneratedGround == null)
            return 0;

        return getRightPosition(lastGeneratedGround) + MINIMAL_SPACE_BETWEEN_GROUND + (float) Math.random() * MAXIMAL_SPACE_BETWEEN_GROUND_WITHOUT_MINIMAL_SPACE;
    }

    protected float generateGroundSize() {
        return MINIMAL_GROUD_SIZE + (float) Math.random() * MAXIMAL_SPACE_BETWEEN_GROUND_WITHOUT_MINIMAL_SPACE;
    }

    protected ArrayList<GameActor> generatePassableObstacles(GameActor ground) {
        ArrayList<GameActor> obstacles = new ArrayList<GameActor>();
        int count = Math.round((float) Math.random() * 2.0f);

        float lastPosition = ground.getBox().getPosition()._x;
        for (int i = 0; i < count; i++) {
            float position = lastPosition + MINIMAL_SPACE * 1.5f + (float) Math.random() * MAXIMAL_SPACE_WITHOUT_MINIMAL;
            if (position + MINIMAL_SPACE * 1.5f < getRightPosition(ground)) {
                obstacles.add(new Ventilation(new Vector(position, ground.getBox().getSize()._y), new Vector(MINIMAL_SPACE * 1.5f, MINIMAL_SPACE)));
                lastPosition = position;
            }
        }

        return obstacles;
    }

    protected ArrayList<GameActor> generateImpassableObstacles(GameActor ground) {
        ArrayList<GameActor> obstacles = new ArrayList<GameActor>();
        int count = Math.round((float) Math.random() * 2.0f);

        float lastPosition = ground.getBox().getPosition()._x;
        for (int i = 0; i < count; i++) {
            float position = lastPosition + MINIMAL_SPACE * 2.0f + (float) Math.random() * MAXIMAL_SPACE_WITHOUT_MINIMAL;
            if (position + MINIMAL_SPACE * 2.0f < getRightPosition(ground)) {
                obstacles.add(new Trumpet(new Vector(position, ground.getBox().getSize()._y), new Vector(MINIMAL_SPACE * 2.0f, MINIMAL_SPACE * 0.5f)));
                lastPosition = position;
            }
        }

        return obstacles;
    }


    public float generateFoodPosition() {
        if (lastGeneratedFood == null)
            return 5f;
        return lastGeneratedFood.getBox().getPosition()._x + (float) Math.random() * 6 + Constants.FOOD_WIDTH * 2;
    }

    public GameActor generateNewFood() {
        GameActor actor;

        float cameraRightPosition = GameActor.transformFromScreen(camera.position.x + camera.viewportWidth);
        if (lastGeneratedFood != null && getRightPosition(lastGeneratedFood) > cameraRightPosition )
            return null;

        double probability = Math.random();

        if (probability < 0.4) {
            actor = new Milk(new Vector(generateFoodPosition(), 10.0f));
        } else {
            actor = new Meat(new Vector(generateFoodPosition(), 10.0f));
        }

        lastGeneratedFood = actor;


        return actor;
    }

    public float generateEnemyPosition() {
        if (lastGeneratedEnemy == null)
            return 6f;
        return lastGeneratedEnemy.getBox().getPosition()._x + (float) Math.random() * 8 + Constants.ENEMY_WIDTH * 4;
    }

    public GameActor generateEnemies() {
        GameActor actor;
        float cameraRightPosition = GameActor.transformFromScreen(camera.position.x + camera.viewportWidth);
        if (lastGeneratedEnemy != null && getRightPosition(lastGeneratedEnemy) > cameraRightPosition)
            return null;

        double probability = Math.random();

        if (probability < 0.2) {
            float attackPosition = 2 * GameActor.transformFromScreen(camera.viewportWidth) / 3;
            attackPosition = (float) Math.random() * attackPosition;
            Vector position = new Vector(generateEnemyPosition(), 5);
            actor = new Bird(position, position.getX() - attackPosition);
        } else {
            actor = new Raccoon(new Vector(generateEnemyPosition(), 10.0f));
        }

        lastGeneratedEnemy = actor;

        return actor;
    }


    public ArrayList<GameActor> generateObstacles(GameActor ground) {
        ArrayList<GameActor> obstacles = new ArrayList<GameActor>();
        obstacles.addAll(generatePassableObstacles(ground));
        obstacles.addAll(generateImpassableObstacles(ground));
        return obstacles;
    }

    protected float getRightPosition(GameActor actor) {
        if (actor == null)
            return 0;

        return actor.getBox().getPosition()._x + actor.getBox().getSize()._x;
    }
}
