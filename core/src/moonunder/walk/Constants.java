package moonunder.walk;

/**
 * Created by pyc6eh4uk on 07.05.17.
 */

public class Constants {

    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;

    public static final float RUNNER_WIDTH = 1.68f;
    public static final float RUNNER_HEIGHT = 1.2f;

    public static final float FOOD_WIDTH = 0.9f;
    public static final float FOOD_HEIGHT = 0.8f;

    public static final float ENEMY_WIDTH = 1.3f;
    public static final float ENEMY_HEIGHT = 1.0f;

    public static final float BIG_ROOF_WIDTH = 1.5f;
    public static final float BIG_ROOF_HEIGHT = 1.5f;

    public static float RUNNER_DENSITY = 0.5f;

    public static String GRAY_CAT_ATLAS_PATH = "gray_cat_spritesheet.txt";
    public static String BROWN_CAT_ATLAS_PATH = "other/gray_cat_spritesheet.txt";
    public static String RACCOON_ATLAS_PATH = "enemy/raccoon_spritesheet.txt";
    public static String FLYING_BIRD_PATH = "enemy/enemy_spritesheet.txt";

    public static final String BACKGROUND_IMAGE_PATH = "images/cityBkg.png";

    public  String[] GRAY_CAT_RUNNING_REGION_NAMES = new String[] {
            "gray_cat_running1",
            "gray_cat_running2",
            "gray_cat_running3"
    };
    public  String[] GRAY_CAT_JUMPING_REGION_NAMES = new String[] {
            "gray_cat_jumping1",
            "gray_cat_jumping2",
            "gray_cat_jumping3"
    };

    public static String[] FLYING_BIRD_REGION_NAMES = new String[] {
            "brid1",
            "bird2",
            "bird3"
    };

    public static String[] RACCOON_REGION_NAMES = new String[] {
            "raccoon",
            "raccoon_falling",
            "raccoon_in_air"
    };

    public static String[] BROWN_CAT_RUNNING_REGION_NAMES = new String[] {
            "gray_cat_running1",
            "gray_cat_running2",
            "gray_cat_running3"
    };
    public static  String[] BROWN_CAT_JUMPING_REGION_NAMES = new String[] {
            "gray_cat_jumping1",
            "gray_cat_jumping2",
            "gray_cat_jumping3"
    };
}
