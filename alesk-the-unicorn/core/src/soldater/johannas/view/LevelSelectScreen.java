package soldater.johannas.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import soldater.johannas.AleskTheUnicorn;
import soldater.johannas.control.GameController;

import java.util.ArrayList;
import java.util.List;

public class LevelSelectScreen implements Screen {

    private final AleskTheUnicorn game;
    private List<LevelInfo> levels = new ArrayList<>();
    private int startY = 500;

    public LevelSelectScreen(final AleskTheUnicorn game) {
        this.game = game;

        FileHandle[] internalFiles = Gdx.files.local("levels/").list();

        for(FileHandle file : internalFiles) {
            if(file.nameWithoutExtension().endsWith(".meta")) {
                levels.add(new LevelInfo(file));
            }
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        int i = 0;
        for(LevelInfo level : this.levels) {
            game.font.draw(game.batch, level.getName(), 100, startY + i*20);
            i++;
        }
        game.batch.end();

        if(Gdx.input.isTouched()) {
            game.setScreen(new GameController(game, levels.get(0).getFileName()));
            dispose();
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
