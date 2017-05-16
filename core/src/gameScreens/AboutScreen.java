package gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by pyc6eh4uk on 15.05.17.
 */

public class AboutScreen implements Screen {

    private StartGame game;
    private Stage stage;
    private SpriteBatch batch;
    private BitmapFont font;
    private Sprite sprite;
    private String string;
    private float specialScale;
    private ImageButton backBtn;

    public AboutScreen(StartGame game) {
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();
        font = new BitmapFont();
        specialScale = stage.getHeight() / 6.0f / new Texture("life/heart.png").getHeight();

        string = "Touch on screen to JUMP!" + "\n" + "Avoid ENEMYies!" + "\n" +
                "Take food!" + "\n" + "Enjoy Walking Under Moon!";
        createBackgroundForScore();
        font.getData().setScale(specialScale / 2.0f);
        font.setColor(Color.GREEN);
        goToMainMenuScreen();
        Gdx.input.setInputProcessor(stage);
    }

    private void goToMainMenuScreen() {
        final TextureRegion backTexture = new TextureRegion(new Texture("buttons/back.png"));
        final float scale = stage.getHeight() / 6.0f / new Texture("buttons/back.png").getHeight();
        backBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/back.png"))))) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                setPosition(stage.getWidth() - backBtn.getWidth() * scale / 1.5f, stage.getHeight() - backBtn.getHeight() * scale / 1.5f);
                batch.draw(backTexture, stage.getWidth() - backBtn.getWidth() * scale / 1.5f, stage.getHeight() - backBtn.getHeight() * scale / 1.5f ,
                        backBtn.getWidth() * scale / 1.5f, backBtn.getHeight() * scale / 1.5f);
            }
        };
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        stage.addActor(backBtn);
    }

    private void createBackgroundForScore() {
        sprite = new Sprite(new TextureRegion(new Texture(Gdx.files.internal("images/helpBkg.jpg"))));
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
//
//        batch.begin();
//        font.draw(batch, string,
//                stage.getWidth() / 2.3f - font.getLineHeight() * 2 - specialScale * 2.0f,
//                stage.getHeight() / 1.5f + font.getLineHeight() + specialScale * 1.5f);
//        batch.end();

        stage.act(delta);
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
        stage.dispose();
    }
}
