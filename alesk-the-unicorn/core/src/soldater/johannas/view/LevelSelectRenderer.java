package soldater.johannas.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.control.menu.LevelSelection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelSelectRenderer extends ScreenRenderer {
    private int START_Y = Gdx.graphics.getHeight() * 3 / 4;
    private final int MARGIN = 40;

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
                    2,
                    MenuItem.Alignment.LEFT,
                    new Color(247.0f / 255, 235.0f / 255, 108.0f / 255, 255)
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
        for (int i = 0; i < levels.size(); i++) {
            if (i == selectItem) {
                renderSelected();
            } else {
                items.get(i).draw(
                        batch,
                        100,
                        START_Y - (items.get(i).getHeight() + MARGIN) * i
                );
            }
        }
    }

    private void renderSelected() {
        int offsetX = (int)(Math.sin(((double) frame / N_FRAMES) * (Math.PI * 2)) * 3);
        int offsetY = (int)(Math.cos(((double) frame / N_FRAMES) * (Math.PI * 2)) * 5);

        items.get(selectItem).draw(
                batch,
                100 + offsetX,
                START_Y - (items.get(selectItem).getHeight() + MARGIN) * selectItem + offsetY
        );
    }
}
