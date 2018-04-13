package soldater.johannas.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.control.menu.LevelSelection;

import java.util.List;

public class LevelSelector implements Screen {
    private LevelSelection levelSelection;
    private SpriteBatch batch;
    private BitmapFont font;
    private List<LevelInfo> levels;

    private int startY = 500;

    public LevelSelector(LevelSelection levelSelection, List<LevelInfo> levels) {
        this.levelSelection = levelSelection;
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.levels = levels;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        int i = 0;
        for(LevelInfo level : levels) {
            font.draw(batch, level.getName(), 100, startY + i*20);
            i++;
        }
        batch.end();

        if(Gdx.input.isTouched()) {
            levelSelection.startLevel(levels.get(0).getFileName());
        }
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

    }
}
