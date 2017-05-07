package moonunder.walk;

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

    protected GameActor lastGeneratedFood;
    protected GameActor lastGeneratedGround;
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

    public ArrayList<GameActor> generateOstacles(GameActor ground) {
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
