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
    protected static final float CAMERA_SPEED = GameActor.transformToScreen(1.0f);

    public GameStage() {
        gameActors = new ArrayList<GameActor>();
        movementManager = new GameActorMovementManager();
        runner = new Runner(new Vector(0, 3));

        Ground ground = new Ground(new Vector(0, 0), new Vector(10, 2));
        Milk milk = new Milk(new Vector(6, 10));
        Ventilation ventilation = new Ventilation(new Vector(5, 4), new Vector(2, 1.5f));
        Trumpet trumpet = new Trumpet(new Vector(8, 2), new Vector(1, 1));

        addActor(runner);
        addActor(ventilation);
        addActor(ground);
        addActor(milk);
        addActor(trumpet);

        runner.setZIndex(ventilation.getZIndex() + 1);

        gameActors.add(runner);
        gameActors.add(ground);
        gameActors.add(milk);
        gameActors.add(ventilation);
        gameActors.add(trumpet);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
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

    public void onCollision(Collision collision) {
        if (collision.getActorA() instanceof Runner) {
            Runner runner = (Runner) collision.getActorA();

            if (collision.getActorB() instanceof Ground) {
                runner.land();
                return;
            }

            if (collision.getActorB() instanceof Food) {
                System.out.println("I EAT FOODDDD!!!1");
                gameActors.remove(collision.getActorB());
                collision.getActorB().remove();
                return;
            }

        }


        if (collision.getActorA() instanceof Food) {
            Food food = (Food) collision.getActorA();

            if (collision.getActorB() instanceof Runner) {
                Runner runner = (Runner) collision.getActorB();

                System.out.println("I EAT FOODDDD!!!2");
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
