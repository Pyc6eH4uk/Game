package moonunder.walk;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;


/**
 * Created by gdhsnlvr on 06.05.17.
 */

public class GameActorMovementManager {
    public GameActorMovementManager() {

    }

    public ArrayList<Collision> move(ArrayList<GameActor> actors, int iteration_count, float deltaTime) {
        ArrayList<Collision> collisions = new ArrayList<Collision>();

        for (GameActor actor : actors) {
            if (actor._static)
                continue;

            ArrayList<Collision> objectCollisions = move(actor, actors, iteration_count, deltaTime);
            collisions.addAll(objectCollisions);
        }

        return collisions;
    }

    public ArrayList<Collision> move(GameActor movingActor, ArrayList<GameActor> actors, int iteration_count, float deltaTime) {
        Vector speed = movingActor.getSpeed().multiply(deltaTime);
        Vector speedX = new Vector(speed.getX(), 0);
        Vector speedY = new Vector(0, speed.getY());

        ArrayList<Collision> collisions = new ArrayList<Collision>();
        LinkedHashSet<GameActor> collideSet = new LinkedHashSet<GameActor>();

        int minimalStepX = iteration_count, minimalStepY = iteration_count;
        for (int i = 0; collisions.size() == 0 && i <= iteration_count; i++) {
            Box afterMoveX = movingActor.getBox().move(
                    speedX.devide(iteration_count).multiply(i)
            );
            Box afterMoveY = movingActor.getBox().move(
                    speedY.devide(iteration_count).multiply(i)
            );

            for (GameActor actor : actors) {
                if (actor == movingActor) continue;

                if (actor._passable) {
                    if (movingActor.getBox().getPosition()._y >= actor.getBox().getPosition()._y + actor.getBox().getSize()._y) {
                        if (Box.overlaps(afterMoveX, actor.getBox())) {
                            minimalStepX = Math.min(minimalStepX, i);
                            collideSet.add(actor);
                        }
                        if (Box.overlaps(afterMoveY, actor.getBox())) {
                            minimalStepY = Math.min(minimalStepY, i);
                            collideSet.add(actor);
                        }
                    }
                } else {
                    if (Box.overlaps(afterMoveX, actor.getBox())) {
                        minimalStepX = Math.min(minimalStepX, i);
                        collideSet.add(actor);
                    }
                    if (Box.overlaps(afterMoveY, actor.getBox())) {
                        minimalStepY = Math.min(minimalStepY, i);
                        collideSet.add(actor);
                    }
                }
            }
        }

        minimalStepX = Math.max(0, minimalStepX - 1);
        minimalStepY = Math.max(0, minimalStepY - 1);

        Box resultBox = movingActor.getBox().move(new Vector(
                speedX.getX() / iteration_count * minimalStepX,
                speedY.getY() / iteration_count * minimalStepY
        ));

        boolean wasCollide = false;
        for (GameActor actor : actors) {
            if (actor == movingActor) continue;

            if (actor._passable) {
                if (movingActor.getBox().getPosition()._y >= actor.getBox().getPosition()._y + actor.getBox().getSize()._y) {
                    if (Box.overlaps(resultBox, actor.getBox())) {
                        wasCollide = true;
                        collideSet.add(actor);
                    }
                }
            } else {
                if (Box.overlaps(resultBox, actor.getBox())) {
                    wasCollide = true;
                    collideSet.add(actor);
                }
            }
        }

        if (!wasCollide) {
            movingActor.setBox(resultBox);
        }

        for (GameActor actor : collideSet) {
            collisions.add(new Collision(movingActor, actor));
        }

        return collisions;
    }
}