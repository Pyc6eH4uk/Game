package gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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
    private ImageButton backBtn, firstCatButton, secondCatButton, thirdCatButton;
    private Stage stage;
    private Skin skin;
    private Table table;
    private Label label;
    private Dialog dialog;
    private Label label1;
    private StartGame game;
    private Sprite sprite;
    private SpriteBatch batch;
    final float scaleBtn;
    public ShopScreen(StartGame game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        table = new Table();
        skin = new Skin(Gdx.files.internal("other/uiskin.json"));
        dialog = new Dialog("Choisen cat:", skin);
        dialog.setMovable(false);
        scaleBtn = stage.getHeight() / 1.5f / new Texture("images/cat1.png").getHeight();
//        createBackButton();
        goToBackMenu();
        label1 = new Label("First cat", skin);
        createCatsForUsersChoice();
        batch = new SpriteBatch();
        setNameForShopScreen();
        createBackgroundForShop();
    }

//    private void createBackButton() {
//        button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/backButton.jpg"))));
//        button.setPosition(stage.getWidth() - button.getWidth(), stage.getHeight() - button.getHeight());
//        button.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent inputEvent, float x, float y) {
//                game.setScreen(new MainMenuScreen(game));
//            }
//        });
//        stage.addActor(button);
//    }

    private void createBackgroundForShop() {
        sprite = new Sprite(new TextureRegion(new Texture(Gdx.files.internal("images/backMenu.png"))));
    }

    private void goToBackMenu() {
        final TextureRegion backTexture = new TextureRegion(new Texture("buttons/back.png"));
        final float scale = stage.getHeight() / 6.0f / new Texture("buttons/back.png").getHeight();
        backBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("buttons/back.png"))))) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                setPosition(stage.getWidth() - backBtn.getWidth() * scale / 1.5f, stage.getHeight() - backBtn.getHeight() * scale / 1.5f);
                batch.draw(backTexture, stage.getWidth() - backBtn.getWidth() * scale / 1.5f, stage.getHeight() - backBtn.getHeight() * scale / 1.5f ,
                        backBtn.getWidth() * scale / 1.5f, backBtn.getHeight() * scale / 1.5f);
//                setPosition( scale / 1.5f,  scale / 1.5f);
//                batch.draw(backTexture, scale / 1.5f,  scale / 1.5f ,
//                        backBtn.getWidth() * scale / 1.5f, backBtn.getHeight() * scale / 1.5f);
            }
        };
        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        stage.addActor(backBtn);
        System.out.println(stage.getWidth() - backBtn.getWidth() * scale / 1.5f);
        System.out.println(stage.getHeight() - backBtn.getHeight() * scale / 1.5f);
    }
    public void createCatsForUsersChoice() {
        final TextureRegion cat1Texture = new TextureRegion(new Texture("images/cat1.png"));
        firstCatButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/cat1.png")))) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                setPosition(stage.getWidth() / 2.5f - firstCatButton.getWidth() * scaleBtn / 1.5f, stage.getHeight() / 1.5f - firstCatButton.getHeight() * scaleBtn / 2f);
                batch.draw(cat1Texture, stage.getWidth() / 2.5f - firstCatButton.getWidth() * scaleBtn / 1.5f, stage.getHeight() / 1.5f - firstCatButton.getHeight() * scaleBtn / 2f ,
                        firstCatButton.getWidth() * scaleBtn / 1.5f, firstCatButton.getHeight() * scaleBtn / 1.5f);
            }
        };
//        firstCatButton.setHeight(200);
//        firstCatButton.setWidth(200);
//        firstCatButton.setPosition(stage.getWidth() / 3.0f - firstCatButton.getWidth(), stage.getHeight() / 1.5f - firstCatButton.getHeight());
        firstCatButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Choice cat");
                dialog.text(label1);
                dialog.setScale(scaleBtn);
                dialog.setPosition(50, 50);
                dialog.show(stage);
                Constants.BROWN_CAT_ATLAS_PATH = "other/gray_cat_spritesheet.txt";
                Constants.BROWN_CAT_RUNNING_REGION_NAMES = new String[] {"gray_cat_running1", "gray_cat_running2", "gray_cat_running3"};
                Constants.BROWN_CAT_JUMPING_REGION_NAMES = new String[] {"gray_cat_jumping1",
                        "gray_cat_jumping2", "gray_cat_jumping3"
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
        final TextureRegion cat2Texture = new TextureRegion(new Texture("images/cat2.png"));
        final float firstCatPosW = stage.getWidth() / 2.5f - firstCatButton.getWidth() * scaleBtn / 1.5f;
        final float firstCatPosH = stage.getHeight() / 1.5f - firstCatButton.getHeight() * scaleBtn / 1.5f;
        secondCatButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/cat2.png")))) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                setPosition(firstCatPosW + secondCatButton.getWidth() / 2 + scaleBtn / 1.5f, stage.getHeight() / 1.5f - firstCatButton.getHeight() * scaleBtn / 2f);
                batch.draw(cat2Texture,firstCatPosW + secondCatButton.getWidth() / 2 + scaleBtn / 1.5f, stage.getHeight() / 1.5f - firstCatButton.getHeight() * scaleBtn / 2f,
                        secondCatButton.getWidth() * scaleBtn / 1.5f, secondCatButton.getHeight() * scaleBtn / 1.5f);
            }
        };
        final TextureRegion cat3Texture = new TextureRegion(new Texture("images/cat3.png"));
        thirdCatButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("images/cat3.png")))) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                setPosition(firstCatPosW + secondCatButton.getWidth() / 2 + scaleBtn / 1.5f + thirdCatButton.getWidth() / 2,stage.getHeight() / 1.5f - firstCatButton.getHeight() * scaleBtn / 2f);
//                setPosition(firstCatPosW + thirdCatButton.getWidth() * 1 + scaleBtn, firstCatPosH + scaleBtn / 1.5f);
                batch.draw(cat3Texture,firstCatPosW + secondCatButton.getWidth() / 2 + scaleBtn / 1.5f + thirdCatButton.getWidth() / 2,stage.getHeight() / 1.5f - firstCatButton.getHeight() * scaleBtn / 2f,
                        thirdCatButton.getWidth() * scaleBtn / 1.5f, thirdCatButton.getHeight() * scaleBtn / 1.5f);
            }
        };
        thirdCatButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent inputEvent, float x, float y) {
                System.out.println("Clicked");
            }
        });
        thirdCatButton.setScale(scaleBtn);
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
