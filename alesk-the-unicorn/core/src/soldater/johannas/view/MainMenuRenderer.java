package soldater.johannas.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.control.menu.MainMenu;

public class MainMenuRenderer implements Screen {
    private MainMenu mainMenu;
    private SpriteBatch batch;
    private BitmapFont font;

    public MainMenuRenderer(MainMenu menu) {
        this.mainMenu = menu;
        batch = new SpriteBatch();
        font = new BitmapFont();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.2f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        font.draw(batch, "Start Game", Gdx.graphics.getWidth() / 2, 500);

        if (Gdx.input.justTouched()) {
            mainMenu.startGame();
        }

        batch.end();
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
