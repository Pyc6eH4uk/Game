package moonunder.walk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;

import java.util.ArrayList;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class Generator {

    protected static final float MINIMAL_SPACE_BETWEEN_GROUND = 1.0f * Constants.RUNNER_WIDTH;
    protected static final float MAXIMAL_SPACE_BETWEEN_GROUND_WITHOUT_MINIMAL_SPACE = 1.0f * Constants.RUNNER_WIDTH;
    protected static final float MINIMAL_GROUD_SIZE = 6.0f * Constants.RUNNER_WIDTH;
    protected static final float MAXIMAL_GROUD_SIZE_WITHOUT_MINIMAL = 4.0f * Constants.RUNNER_WIDTH;
    protected static final float MINIMAL_SPACE = 1.0f * Constants.RUNNER_WIDTH;
    protected static final float MAXIMAL_SPACE_WITHOUT_MINIMAL = 3.0f * Constants.RUNNER_WIDTH;

    protected static final float MIN_SPACE_BETWEEN_FOOD = 1.0f * Constants.FOOD_WIDTH;

    protected GameActor lastGeneratedFood;
    protected GameActor lastGeneratedGround;
    protected GameActor lastGeneratedPassableObstacle;
    protected GameActor lastGeneratedImpassableObstacle;

    protected Camera camera;

    public Generator(Camera camera) {
        this.camera = camera;
    }

    public GameActor generateAllFood() {

        float cameraRightPosition = GameActor.transformFromScreen(camera.position.x + camera.viewportWidth);
        if (lastGeneratedFood != null && getRightPosition(lastGeneratedFood) > cameraRightPosition)
            return null;

        lastGeneratedFood = new Food(new Vector(generateGroundPosition(), 0));
        return lastGeneratedFood;
    }

    public GameActor generateGround() {
        float cameraRightPosition = GameActor.transformFromScreen(camera.position.x + camera.viewportWidth);
        if (lastGeneratedGround != null && getRightPosition(lastGeneratedGround) > cameraRightPosition)
            return null;

        lastGeneratedGround = new Ground(new Vector(generateGroundPosition(), 0), new Vector(generateGroundSize(), 1));
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
            float position = lastPosition + MINIMAL_SPACE + (float) Math.random() * MAXIMAL_SPACE_WITHOUT_MINIMAL;
            if (position + MINIMAL_SPACE < getRightPosition(ground)) {
                obstacles.add(new Ventilation(new Vector(position, 1), new Vector(MINIMAL_SPACE * 1.5f, MINIMAL_SPACE)));
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
            float position = lastPosition + MINIMAL_SPACE + (float) Math.random() * MAXIMAL_SPACE_WITHOUT_MINIMAL;
            if (position + MINIMAL_SPACE < getRightPosition(ground)) {
                obstacles.add(new Trumpet(new Vector(position, 1), new Vector(MINIMAL_SPACE * 2.0f, MINIMAL_SPACE * 0.5f)));
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
        }

        else  {
            actor = new Meat(new Vector(generateFoodPosition(), 10.0f));
        }

        lastGeneratedFood = actor;


        return actor;
    }

    protected ArrayList<GameActor> generateFood(GameActor food) {
        ArrayList<GameActor> foods = new ArrayList<GameActor>();
        System.out.println("Generate food");
        float probability = (float) Math.random();
        float lastPosition = food.getBox().getPosition()._x;
        float position = lastPosition -  getRightPosition(food) / 4.0f;
        System.out.println("lastPosition =" + lastPosition);
        System.out.println(position);
        if (position < getRightPosition(food)) {
            if (probability < 0.5) {
                foods.add(new Milk(new Vector(position, 1)));
            } else {
                foods.add(new Meat(new Vector(position, 1)));
            }
        }

        return foods;
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
