package gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import moonunder.walk.Constants;

/**
 * Created by pyc6eh4uk on 12.05.17.
 */

public class ShopScreen implements Screen {
    private Texture myTexture;
    private TextureRegion myTextureRegion;
    private TextureRegionDrawable myTexRegionDrawable;
    private ImageButton button, firstCatButton, secondCatButton, thirdCatButton;
    private Stage stage;
    private Skin skin;
    private Table table;
    private Label label;
    private Dialog dialog;
    private Label label1;
    private StartGame game;
    private Sprite sprite;
    private SpriteBatch batch;

    public ShopScreen(StartGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        table = new Table();
        skin = new Skin(Gdx.files.internal("other/uiskin.json"));
        dialog = new Dialog("Choisen cat:", skin);
        dialog.setMovable(false);
        createBackButton();
        label1 = new Label("First cat", skin);
        createCatsForUsersChoice();
        batch = new SpriteBatch();
        setNameForShopScreen();
        createBackgroundForShop();
    }

    private void createBackButton() {
        button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/backButton.jpg"))));
        button.setPosition(stage.getWidth() - button.getWidth(), stage.getHeight() - button.getHeight());
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        stage.addActor(button);
    }

    private void createBackgroundForShop() {
        sprite = new Sprite(new TextureRegion(new Texture(Gdx.files.internal("images/backMenu.png"))));
    }

    public void createCatsForUsersChoice() {
        firstCatButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/cat1.png"))));
        firstCatButton.setHeight(200);
        firstCatButton.setWidth(200);
        firstCatButton.setPosition(stage.getWidth() / 3.0f - firstCatButton.getWidth(), stage.getHeight() / 1.5f - firstCatButton.getHeight());
        firstCatButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Choice cat");
                dialog.text(label1);
                dialog.setPosition(50, 50);
                dialog.show(stage);
                Constants.GRAY_CAT_ATLAS_PATH = "other/brown_cat_spritesheet.txt";
                Constants.GRAY_CAT_RUNNING_REGION_NAMES = new String[] {"brown_cat_running1", "brown_cat_running2", "brown_cat_running3"};
                Constants.GRAY_CAT_JUMPING_REGION_NAMES = new String[] {"brown_cat_jumping1",
                        "brown_cat_jumping2", "brown_cat_jumping3"
                };
//                Constants.GRAY_CAT_HIT_REGION_NAME = "brown_cat";
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        dialog.hide();
                        dialog.cancel();
                        dialog.remove();
                    }
                }, 2);
            }
        });
        stage.addActor(firstCatButton);
        secondCatButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/cat2.png"))));
        secondCatButton.setHeight(200);
        secondCatButton.setWidth(200);
        float firstCatPosW = stage.getWidth() / 3.0f - firstCatButton.getWidth();
        float firstCatPosH = stage.getHeight() / 1.5f - firstCatButton.getHeight();
        secondCatButton.setPosition(firstCatPosW + secondCatButton.getWidth(), firstCatPosH);
        thirdCatButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/cat3.png"))));
        thirdCatButton.setHeight(200);
        thirdCatButton.setWidth(200);
        thirdCatButton.setPosition(firstCatPosW + secondCatButton.getWidth() + thirdCatButton.getWidth(), firstCatPosH);
        stage.addActor(secondCatButton);
        stage.addActor(thirdCatButton);
    }


    private void setNameForShopScreen() {
        label = new Label("Shop(russkii language ne supported)", skin);
        label.scaleBy(2f);
        label.setPosition(stage.getWidth() / 2, stage.getHeight() - label.getHeight());
        stage.addActor(label);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(1, 0, 0, 1);
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
        stage.dispose();
    }
}
