package moonunder.walk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by pyc6eh4uk on 08.05.17.
 */

public class Background extends GameActor {
    private Box leftBounds, rightBounds;
    private Texture texture;
    private final TextureRegion textureRegion;
    private Camera camera;

    public Background(Camera camera) {
        super(new Box(new Vector(0, 0), new Vector(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())), new Vector(0, 0));

        texture = new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH));
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH)));
        this.camera = camera;

        this.sprite = new Sprite(texture);
        leftBounds = new Box(new Vector(0, 0), new Vector(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        rightBounds = new Box(new Vector(Gdx.graphics.getWidth(), 0), new Vector(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    @Override
    public void act(float delta) {
        if (leftBoundsReached(delta)) {
            resetBounds();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.draw(textureRegion, leftBounds.getPosition()._x, leftBounds.getPosition()._y,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch.draw(textureRegion, rightBounds.getPosition()._x, rightBounds.getPosition()._y,
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private boolean leftBoundsReached(float delta) {
        return rightBounds.getPosition()._x < camera.position.x - camera.viewportWidth / 2f;
    }

    private void resetBounds() {
        leftBounds = new Box(new Vector(camera.position.x - camera.viewportWidth / 2f, 0), new Vector(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        rightBounds = new Box(new Vector(camera.position.x + Gdx.graphics.getWidth() - camera.viewportWidth / 2f, 0), new Vector(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }
}
