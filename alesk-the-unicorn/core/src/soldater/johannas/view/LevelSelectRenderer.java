package soldater.johannas.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.control.menu.LevelSelection;
import soldater.johannas.util.Colors;

import java.util.ArrayList;
import java.util.List;

public class LevelSelectRenderer extends ScreenRenderer {
    private final int SELECTED_LEVEL_Y = Gdx.graphics.getHeight() / 2;
    private final int SELECTED_LEVEL_X = 400;

    private final int MARGIN = 20;

    private LevelSelection levelSelection;
    private SpriteBatch batch;
    private BitmapFont font;

    private Texture textBackground;
    private List<LevelInfo> levels;
    private List<MenuItem> items;
    private int selectItem = 0;

    public LevelSelectRenderer(LevelSelection levelSelection, List<LevelInfo> levels) {
        this.levelSelection = levelSelection;
        batch = new SpriteBatch();
        font = new BitmapFont();

        this.levels = levels;
        items = new ArrayList<>();

        for (LevelInfo levelInfo : levels) {
            items.add(new MenuItem(
                    levelInfo.getName().replace("_", " "),
                    3,
                    MenuItem.Alignment.CENTER,
                    Colors.MENU_COLOR
            ));
        }

        loadTextures();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        checkInput();

        batch.begin();

        batch.draw(
                textBackground,
                0,
                0,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        );

        renderLevels();

        batch.end();
    }

    private void loadTextures() {
        textBackground = new Texture("menu/selectLevelBackground.png");
    }

    private void checkInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectItem = selectItem - 1;
            if (selectItem < 0) {
                selectItem += levels.size();
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectItem = (selectItem + 1) % levels.size();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            levelSelection.goBack();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            levelSelection.startLevel(levels.get(selectItem).getFileName());
        }
    }

    private void renderLevels() {

        int next = selectItem - 1 < 0 ? items.size() - 1 : selectItem - 1;
        int prev = selectItem + 1 == items.size() ? 0 : selectItem + 1;

        renderSelected();

        items.get(prev).draw(
                batch,
                SELECTED_LEVEL_X,
                SELECTED_LEVEL_Y - items.get(selectItem).getHeight() - MARGIN,
                2
        );

        items.get(next).draw(
                batch,
                SELECTED_LEVEL_X,
                SELECTED_LEVEL_Y + items.get(selectItem).getHeight() + MARGIN,
                2
        );
    }

    private void renderSelected() {
        int offsetX = (int)(Math.sin(((double) frame / N_FRAMES) * (Math.PI * 2)) * 3);
        int offsetY = (int)(Math.cos(((double) frame / N_FRAMES) * (Math.PI * 2)) * 5);

        items.get(selectItem).draw(
                batch,
                SELECTED_LEVEL_X + offsetX,
                SELECTED_LEVEL_Y
        );
    }
}
