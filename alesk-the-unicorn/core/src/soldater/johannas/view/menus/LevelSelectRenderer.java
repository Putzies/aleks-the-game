package soldater.johannas.view.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.control.menu.LevelSelection;
import soldater.johannas.view.Colors;
import soldater.johannas.view.Highscore;
import soldater.johannas.view.LevelInfo;
import soldater.johannas.view.ScreenRenderer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LevelSelectRenderer extends ScreenRenderer {
    private final int SELECTED_LEVEL_Y = Gdx.graphics.getHeight() / 2;
    private final int SELECTED_LEVEL_X = 250;

    private final int HIGHSCORES_X = Gdx.graphics.getWidth() * 2 / 3;
    private final int HIGHSCORES_TOP_Y = Gdx.graphics.getHeight() * 1 / 4;

    private final int MARGIN = 20;

    private LevelSelection levelSelection;
    private SpriteBatch batch;
    private BitmapFont font;

    private Texture textBackground;
    private List<LevelInfo> levels;
    private List<MenuItem> levelItems;
    private List<MenuItem> highscoreItems;
    private int selectItem = 0;

    public LevelSelectRenderer(LevelSelection levelSelection, List<LevelInfo> levels) {
        this.levelSelection = levelSelection;
        batch = new SpriteBatch();
        font = new BitmapFont();

        this.levels = levels;
        levelItems = new ArrayList<>();
        highscoreItems = new ArrayList<>();

        for (LevelInfo levelInfo : levels) {
            levelItems.add(new MenuItem(
                    levelInfo.getName().replace("_", " "),
                    3,
                    MenuItem.Alignment.CENTER,
                    Colors.MENU_COLOR
            ));
        }

        for (int i = 0; i < 5; i++ ) {
            highscoreItems.add(new MenuItem(
                    "",
                    1.5f,
                    MenuItem.Alignment.LEFT,
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
        renderHighscores();

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
            levelSelection.startLevel(levels.get(selectItem).getName());
        }
    }

    private void renderLevels() {

        int next = selectItem - 1 < 0 ? levelItems.size() - 1 : selectItem - 1;
        int prev = selectItem + 1 == levelItems.size() ? 0 : selectItem + 1;

        renderSelected();

        levelItems.get(prev).draw(
                batch,
                SELECTED_LEVEL_X,
                SELECTED_LEVEL_Y - levelItems.get(selectItem).getHeight() - MARGIN * 5 / 3,
                2,
                0.3f
        );

        levelItems.get(next).draw(
                batch,
                SELECTED_LEVEL_X,
                SELECTED_LEVEL_Y + levelItems.get(selectItem).getHeight() + MARGIN,
                2,
                0.3f
        );
    }

    private void renderSelected() {
        int offsetX = (int)(Math.sin(((double) frame / N_FRAMES) * (Math.PI * 2)) * 3);
        int offsetY = (int)(Math.cos(((double) frame / N_FRAMES) * (Math.PI * 2)) * 5);

        levelItems.get(selectItem).draw(
                batch,
                SELECTED_LEVEL_X + offsetX,
                SELECTED_LEVEL_Y + offsetY
        );
    }

    private void renderHighscores() {
        List<Highscore> highscores = levels.get(selectItem).getHighscores()
                .stream()
                .sorted(Comparator.comparingInt(Highscore::getScore))
                .collect(Collectors.toList());

        if (highscores.size() > 0) {
            for (int i = 0; i < Math.min(highscores.size(), 5); i++) {
                highscoreItems.get(i).setText(highscores.get(i).getFormattedText());
                highscoreItems.get(i).draw(batch, HIGHSCORES_X, HIGHSCORES_TOP_Y - i * MARGIN);
            }
        } else {
            highscoreItems.get(0).setText("No high scores yet!");
            highscoreItems.get(0).draw(batch, HIGHSCORES_X + MARGIN * 2, HIGHSCORES_TOP_Y);
        }
    }
}
