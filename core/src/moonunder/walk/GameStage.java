package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class GameStage extends Stage {
    protected Runner runner;
    protected ArrayList<GameActor> gameActors;
    protected GameActorMovementManager movementManager;
    protected static final float CAMERA_SPEED = -1.0f;

    public GameStage() {
        gameActors = new ArrayList<GameActor>();
        movementManager = new GameActorMovementManager();
        runner = new Runner(new Vector(0, 3));

        Ground ground = new Ground(new Vector(0, 0), new Vector(10, 2));
        Milk milk = new Milk(new Vector(6, 6));
        Ventilation ventilation = new Ventilation(new Vector(5, 2), new Vector(2, 1));

        addActor(runner);
        addActor(ventilation);
        addActor(ground);
        addActor(milk);

        runner.setZIndex(ventilation.getZIndex() + 1);

        gameActors.add(runner);
        gameActors.add(ground);
        gameActors.add(milk);
        gameActors.add(ventilation);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        for (GameActor actor : gameActors) {
            if (!actor._static) {
                actor.setSpeed(actor.getSpeed().add(new Vector(0, -0.1f)));
            }
        }

        ArrayList<Collision> collisions = movementManager.move(gameActors, 6, delta);
        for (Collision collision : collisions) {
            onCollision(collision);
        }

        for (GameActor actor : gameActors) {
            if (actor != runner) {
                actor.setBox(actor.getBox().move(new Vector(CAMERA_SPEED * delta, 0)));
            }
        }

        super.act(delta);
    }

    public void onCollision(Collision collision) {
        if (collision.getActorA() instanceof Runner) {

            Runner runner = (Runner) collision.getActorA();

            if (collision.getActorB() instanceof Ground) {
                runner.land();
                return;
            }

            if (collision.getActorB() instanceof Food) {
                System.out.println("I EAT FOODDDD!!!");
                gameActors.remove(collision.getActorB());
                collision.getActorB().remove();
                return;
            }

        }


        if (collision.getActorA() instanceof Food) {
            Food food = (Food) collision.getActorA();

            if (collision.getActorB() instanceof Runner) {
                Runner runner = (Runner) collision.getActorB();

                System.out.println("I EAT FOODDDD!!!");
                gameActors.remove(food);
                food.remove();
                return;
            }
        }

        GameActor actor = collision.getActorA();
        if (collision.getActorB() instanceof Ground) {
            actor.land();
            return;
        }
        if (collision.getActorB() instanceof Obstacle) {
            Obstacle obstacle = (Obstacle) collision.getActorB();
            if (actor.getBox().getPosition()._y > obstacle.getBox().getPosition()._y + obstacle.getBox().getSize()._y) {
                actor.land();
                return;
            }
        }
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        System.out.println("onTouchDown");

        runner.jump();

        return super.touchDown(x, y, pointer, button);
    }
}
