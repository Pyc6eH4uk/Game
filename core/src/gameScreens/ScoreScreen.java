package gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
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
 * Created by РусБЕНЧик on 13.05.2017.
 */

public class ScoreScreen implements Screen {

    private StartGame game;
    private FileHandle fileHandle;
    private BitmapFont font;
    private SpriteBatch batch;
    private String text;
    private Stage stage;
    private Sprite sprite;
    private boolean exist;
    private ImageButton backBtn;
    private float speialScale;
    private Preferences preferences;
    private String preferencesString;

    public ScoreScreen (StartGame game) {
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();
        text = "";
        font = new BitmapFont();
        preferencesString = "HER";
        createBackgroundForScore();
        preferences = Gdx.app.getPreferences("Scores");
        speialScale = stage.getHeight() / 6.0f / new Texture("life/heart.png").getHeight();
        fileHandle = Gdx.files.local("records/records.txt");
        readFromFile();
        goToBackMenu();
        font.getData().setScale(speialScale / 1.3f);
        font.setColor(Color.GREEN);
        Gdx.input.setInputProcessor(stage);
    }

    private void goToBackMenu() {
        final TextureRegion backTexture = new TextureRegion(new Texture("buttons/newBackBtn.jpg"));
        final float scale = stage.getHeight() / 6.0f / new Texture("buttons/newBackBtn.jpg").getHeight();
        backBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/newBackBtn.jpg"))))) {
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
        sprite = new Sprite(new TextureRegion(new Texture(Gdx.files.internal("images/backMenu.png"))));
    }

    private void readFromFile() {
        preferencesString = preferences.getString("Score: ");
        text = fileHandle.readString();
    }

    public void writeInFile(String writingText) {
        preferences.putString("Score: ", "Score: " + writingText + " ");
        preferences.flush();
        fileHandle.writeString("Score: " + writingText + " " + "\n", true);
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

        batch.begin();
        font.draw(batch, text, stage.getWidth() / 2 - font.getLineHeight() , stage.getHeight() - font.getLineHeight() + speialScale * 1.5f);
        batch.end();

        System.out.print(preferencesString);


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
        stage.dispose();
    }
}
