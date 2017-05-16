package gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by pyc6eh4uk on 12.05.17.
 */

public class MainMenuScreen implements Screen {

    private StartGame game;
    private ImageButton playBtn, scoreBtn, shopBtn, settingBtn, helpBtn;
    public Stage stage;
    private Sprite sprite;
    private SpriteBatch batch;
    private GameScreen screen;

    public MainMenuScreen(StartGame game) {
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage();
        screen = new GameScreen(game, this);
        createMenu();
        createBackgroundForMenu();
        Gdx.input.setInputProcessor(stage);
    }

    private void createBackgroundForMenu() {
        sprite = new Sprite(new TextureRegion(new Texture(Gdx.files.internal("images/backMenu.png"))));
    }

    private void createMenu() {
        final TextureRegion playTexture = new TextureRegion(new Texture("buttons/playBtn.png"));
        final TextureRegion scoreTexture = new TextureRegion(new Texture("buttons/scoreBtn.png"));
        final TextureRegion shopTexture = new TextureRegion(new Texture("buttons/shopBtn.png"));
        final TextureRegion helpTexture = new TextureRegion(new Texture("buttons/helpBtn.png"));
        final TextureRegion settingTexture = new TextureRegion(new Texture("buttons/settingBtn.png"));

        final float scale = stage.getHeight() / 6.0f / new Texture("buttons/playBtn.png").getHeight();
        playBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/playBtn.png")))) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                setPosition(stage.getWidth() / 2 - playBtn.getWidth() * scale / 2, stage.getHeight() / 2 - playBtn.getHeight() * scale / 2);
                batch.draw(playTexture, stage.getWidth() / 2 - playBtn.getWidth() * scale / 2, stage.getHeight()/2 - playBtn.getHeight() * scale / 2,
                        playBtn.getWidth() * scale, playBtn.getHeight() * scale);
            }
        };
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                game.setScreen(screen);
            }
        });

        helpBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/helpBtn.png")))) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                setPosition(stage.getWidth() - helpBtn.getWidth() * scale, scale / 2);
                batch.draw(helpTexture, stage.getWidth() - helpBtn.getWidth() * scale,  scale / 2,
                        helpBtn.getWidth() * scale, helpBtn.getHeight() * scale);
            }
        };
        helpBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                game.setScreen(new AboutScreen(game));
            }
        });

        scoreBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/scoreBtn.png")))) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
//                setPosition(stage.getWidth() - scoreBtn.getWidth() * scale / 2, stage.getHeight()/2 - scoreBtn.getHeight() * scale / 2);
//                batch.draw(scoreTexture, camera.position.x + camera.viewportWidth / 2 - stage.getWidth(), stage.getHeight() - stage.getHeight(),
//                        scoreBtn.getWidth() * scale, scoreBtn.getHeight() * scale);
                setPosition(stage.getWidth() - scoreBtn.getWidth() * scale - scoreBtn.getWidth() * scale * 1.3f, scale / 2);
                batch.draw(scoreTexture, stage.getWidth() - scoreBtn.getWidth() * scale - scoreBtn.getWidth() * scale * 1.3f, scale / 2,
                        scoreBtn.getWidth() * scale, scoreBtn.getHeight() * scale);
            }
        };
        scoreBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                game.setScreen(new ScoreScreen(game));
            }
        });

        shopBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/shopBtn.png")))) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                setPosition(scale / 2, scale / 2);
                batch.draw(shopTexture, scale / 2, scale / 2,
                        shopBtn.getWidth() * scale, shopBtn.getHeight() * scale);
            }
        };
        shopBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                System.out.println("Shop");
                game.setScreen(new ShopScreen(game));
            }
        });
        settingBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("buttons/settingBtn.png")))) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                setPosition(scale / 2 + settingBtn.getWidth() * scale, 0 - scale * 5f);
//                setPosition(scale / 2 + playBtn.getWidth() / 1.3f,
//                        0 - scale * 5);
//                batch.draw(settingTexture, scale / 2 + settingBtn.getWidth() * scale * 1.3f, scale / 2,
//                        settingBtn.getWidth() * scale, settingBtn.getHeight() * scale);
                batch.draw(settingTexture, scale / 2 + settingBtn.getWidth() * scale,
                            0 - scale * 5f,
                            settingBtn.getWidth() * scale, settingBtn.getHeight() * scale);
            }
        };
        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                game.setScreen(screen);
            }
        });

        playBtn.setScale(scale);
        shopBtn.setScale(scale);
        scoreBtn.setScale(scale);
        helpBtn.setScale(scale);
        settingBtn.setScale(scale);
        stage.addActor(helpBtn);
        stage.addActor(scoreBtn);
        stage.addActor(shopBtn);
        stage.addActor(playBtn);
        stage.addActor(settingBtn);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sprite.draw(batch);
        batch.end();

        stage.act();
        stage.draw();
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
        //stage.dispose();
    }
}
