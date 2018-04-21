package soldater.johannas.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.control.menu.LevelSelection;

import java.util.List;

public class LevelSelectRenderer extends ScreenRenderer {
    private int START_Y = Gdx.graphics.getHeight() / 3;
    private final int MARGIN = 40;

    private LevelSelection levelSelection;
    private SpriteBatch batch;
    private BitmapFont font;

    private Texture textBackground;
    private List<LevelInfo> levels;
    private int selectItem = 0;

    private GlyphLayout layout = new GlyphLayout();

    public LevelSelectRenderer(LevelSelection levelSelection, List<LevelInfo> levels) {
        this.levelSelection = levelSelection;
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.levels = levels;

        layout.setText(font, "Hello");

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
                String levelName = levels.get(i).getName().replace("_", " ");
                layout.setText(font, levelName);

                font.draw(
                        batch,
                        levelName,
                        Gdx.graphics.getWidth() / 2 - layout.width / 2,
                        START_Y - (layout.height + MARGIN) * i
                );
            }
        }
    }

    private void renderSelected() {
        int offsetX = (int)(Math.sin(((double) frame / N_FRAMES) * (Math.PI * 2)) * 3);
        int offsetY = (int)(Math.cos(((double) frame / N_FRAMES) * (Math.PI * 2)) * 5);

        String levelName = levels.get(selectItem).getName().replace("_", " ");
        layout.setText(font, levelName);

        font.draw(
                batch,
                levelName,
                Gdx.graphics.getWidth() / 2 - layout.width / 2 + offsetX,
                START_Y - (layout.height + MARGIN) * selectItem + offsetY
        );
    }
}
