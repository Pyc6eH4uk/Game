package gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import moonunder.walk.GameActor;
import moonunder.walk.GameStage;

/**
 * Created by pyc6eh4uk on 12.05.17.
 */

public class GameScreen implements Screen {

    private ImageButton pauseBtn;
    private StartGame game;
    private Stage stage;
    private GameStage gameStage;
    private MainMenuScreen menuScreen;
    private String result;
    private BitmapFont font;
    private SpriteBatch batch;
    private Sprite life;
    private String score;
//    final float scale = stage.getHeight() / 6.0f / new Texture("life/heart.png").getHeight();
    final float scale;

    public GameScreen(StartGame game, MainMenuScreen menuScreen) {
        this.game = game;
        batch = new SpriteBatch();
        gameStage = new GameStage(game);
        font = new BitmapFont();
        scale = gameStage.getHeight() / 6.0f / new Texture("life/heart.png").getHeight();
        this.menuScreen = menuScreen;
        result = "";
        score = "Score: ";
        life = new Sprite(new Texture(Gdx.files.internal("life/heart.png")));
        pauseListener();
    }


    private void pauseListener() {
        final TextureRegion texture = new TextureRegion(new Texture("buttons/pauseBtn.png"));
        final Camera camera = gameStage.getCamera();
        final float scale = gameStage.getHeight() / 6.0f / new Texture("buttons/pauseBtn.png").getHeight();
        pauseBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/pauseBtn.png")))) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                setPosition(camera.position.x + camera.viewportWidth / 2 - pauseBtn.getWidth() * scale, gameStage.getHeight() - pauseBtn.getHeight() * scale);
                batch.draw(texture, camera.position.x + camera.viewportWidth / 2 - pauseBtn.getWidth() * scale, gameStage.getHeight() - pauseBtn.getHeight() * scale,
                        pauseBtn.getWidth() * scale, pauseBtn.getHeight() * scale);
            }
        };

        pauseBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                game.setScreen(menuScreen);
                Gdx.input.setInputProcessor(menuScreen.stage);
            }
        });
        gameStage.addActor(pauseBtn);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        if (gameStage.lifeCount == 0) {
            game.setScreen(new MainMenuScreen(game));
            return;
        }
        result = Integer.toString(gameStage.score);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameStage.draw();
        gameStage.act(Gdx.graphics.getDeltaTime());
        System.out.println(gameStage.lifeCount);
        for (int i = 0; i < gameStage.lifeCount; i++) {
            batch.begin();
//            life.setScale(scale);
            batch.draw(life, gameStage.getWidth() / 1.5f + i * life.getWidth() * 1.1f, gameStage.getHeight() - life.getHeight(), life.getWidth() * scale, life.getHeight() * scale);
//            life.setScale(scale);
            batch.end();
        }
        batch.begin();
        font.setColor(1, 1, 1, 1);
        font.draw(batch, score + result, gameStage.getWidth() / 2, gameStage.getHeight() - font.getLineHeight());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
