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

        addActor(background);
        addActor(runner);
        gameActors.add(runner);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        generateNewActors();
        for (GameActor actor : gameActors) {
            if (actor instanceof Bird) {
                Bird bird = (Bird) actor;
                if (!bird.isWasAttack() && bird.getBox().getPosition().getX() < bird.getAttackPosition()) {
                    bird.setWasAttack(true);
                    bird.setSpeed(bird.getSpeed().add(new Vector(0, -1.2f)));
                }
                if (bird.isWasAttack() && !bird.isWasDowned() && bird.getBox().getPosition().getY() < GameActor.transformFromScreen(getCamera().viewportHeight) / 3.0f) {
                    bird.setSpeed(bird.getSpeed().add(new Vector(0, 2.4f)));
                    bird.setWasDowned(true);
                }
                if (bird.isWasDowned() && bird.getBox().getPosition().getY() > 6.0f) {
                    bird.setSpeed(bird.getSpeed().add(new Vector(0, -1.2f)));
                }
                continue;
            }
            if (!actor._static) {
                actor.setSpeed(actor.getSpeed().add(new Vector(0, -0.1f)));
            }
        }

        getCamera().translate(CAMERA_SPEED * delta, 0, 0);

        ArrayList<Collision> collisions = movementManager.move(gameActors, 60, delta);
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

        if (runner.getBox().getPosition().getX() + runner.getBox().getSize()._x > GameActor.transformFromScreen(getCamera().position.x + getCamera().viewportWidth / 4)) {
            runner.getBox().getPosition()._x = GameActor.transformFromScreen(getCamera().position.x  + getCamera().viewportWidth / 4) - 1e-2f - runner.getBox().getSize()._x;
        }

        super.act(delta);
    }

    public void generateNewActors() {
        GameActor ground = generator.generateGround();
        GameActor food = generator.generateNewFood();
        GameActor enemy = generator.generateEnemies();

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

        if (enemy != null) {
            addActor(enemy);
            gameActors.add(enemy);
        }
    }

    public void onCollision(Collision collision) {
        if (collision.getActorA()._wasTouch || collision.getActorB()._wasTouch)
            return;

        if (collision.getActorA() instanceof Runner) {
            Runner runner = (Runner) collision.getActorA();

            if (collision.getActorB() instanceof Food) {
                gameActors.remove(collision.getActorB());
                collision.getActorB().remove();
                collision.getActorB()._wasTouch = true;
                return;
            }

            if (collision.getActorB() instanceof Enemy) {
                System.out.println("Uebalsya");
                collision.getActorB()._wasTouch = true;
                return;
            }

        }

        if (collision.getActorA() instanceof Food) {
            Food food = (Food) collision.getActorA();

            if (collision.getActorB() instanceof Runner) {
                Runner runner = (Runner) collision.getActorB();

                gameActors.remove(food);
                food.remove();
                collision.getActorA()._wasTouch = true;
                return;
            }
        }

        if (collision.getActorA() instanceof Enemy) {
            Enemy enemy = (Enemy) collision.getActorA();
            if (collision.getActorB() instanceof Runner) {
                System.out.println("Uebalsya");
                collision.getActorA()._wasTouch = true;
                return;
            }
        }
        GameActor actor = collision.getActorA();
        if (collision.getActorB() instanceof Ground) {
            if (Math.abs(actor.getBox().getPosition()._y - collision.getActorB().getBox().getSize()._y) < 1e-2)
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
