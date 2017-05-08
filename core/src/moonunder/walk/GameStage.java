package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class GameStage extends Stage {
    protected static final float CAMERA_SPEED = GameActor.transformToScreen(1.0f);
    protected GameActorMovementManager movementManager;
    protected Generator generator;

    protected Runner runner;
    protected ArrayList<GameActor> gameActors;
    protected Background background;

    public GameStage() {
        background = new Background(getCamera());

        gameActors = new ArrayList<GameActor>();
        movementManager = new GameActorMovementManager();
        generator = new Generator(getCamera());
        runner = new Runner(new Vector(0, 3));
        Milk milk = new Milk(new Vector(2, 4));

        addActor(milk);
        addActor(background);
        addActor(runner);
        gameActors.add(runner);
        gameActors.add(milk);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        generateNewActors();
//        System.out.println("Runner position" + runner.getBox().getPosition()._x);
        for (GameActor actor : gameActors) {
            if (!actor._static) {
                actor.setSpeed(actor.getSpeed().add(new Vector(0, -0.1f)));
            }
        }

        getCamera().translate(CAMERA_SPEED * delta, 0, 0);

        ArrayList<Collision> collisions = movementManager.move(gameActors, 6, delta);
        for (Collision collision : collisions) {
            onCollision(collision);
        }

        float cameraX = GameActor.transformFromScreen(getCamera().position.x - getWidth() / 2);
        float w = cameraX - runner.getBox().getPosition()._x;
        if (w > 0) {
            boolean collides = false;
            for (GameActor actor : gameActors) {
                if (actor != runner && !actor._passable && Box.overlaps(runner.getBox(), actor.getBox())) {
                    collides = true;
                }
            }
            if (!collides)
                runner.setBox(runner.getBox().move(new Vector(w, 0)));
            else if (w > runner.getBox().getSize()._x) {
                System.out.println("JOPA");
                runner.setBox(runner.getBox().move(new Vector(w, 10)));
            }
        }

        super.act(delta);
    }

    public void generateNewActors() {
        GameActor ground = generator.generateGround();
        GameActor food = generator.generateNewFood();

        if (food != null) {
            addActor(food);
            gameActors.add(food);
        }

        if (ground != null) {
            addActor(ground);
            gameActors.add(ground);

            ArrayList<GameActor> obstacles = generator.generateObstacles(ground);
            for (GameActor obstacle : obstacles) {
                addActor(obstacle);
                runner.setZIndex(obstacle.getZIndex() + 1);
            }
            gameActors.addAll(obstacles);
        }
    }

    public void onCollision(Collision collision) {
        if (collision.getActorA() instanceof Runner) {
            Runner runner = (Runner) collision.getActorA();

            if (collision.getActorB() instanceof Food) {
                gameActors.remove(collision.getActorB());
                collision.getActorB().remove();
                return;
            }

        }

        if (collision.getActorA() instanceof Food) {
            Food food = (Food) collision.getActorA();

            if (collision.getActorB() instanceof Runner) {
                Runner runner = (Runner) collision.getActorB();

                gameActors.remove(food);
                food.remove();
                return;
            }
        }

        GameActor actor = collision.getActorA();
        if (collision.getActorB() instanceof Ground) {
            if (Math.abs(actor.getBox().getPosition()._y - 1) < 1e-2)
                actor.land();
            return;
        }

        if (collision.getActorB() instanceof Obstacle) {
            Obstacle obstacle = (Obstacle) collision.getActorB();
            if (actor.getBox().getPosition()._y > obstacle.getBox().getPosition()._y + obstacle.getBox().getSize()._y) {
                if (actor.getSpeed()._y <= 0) {
                    actor.land();
                }
                return;
            }
        }
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        runner.jump();
        return super.touchDown(x, y, pointer, button);
    }
}
