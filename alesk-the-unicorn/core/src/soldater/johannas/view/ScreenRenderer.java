package soldater.johannas.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public abstract class ScreenRenderer implements Screen {

    protected final int N_FRAMES = 10;
    protected int frame;
    protected float frameCounter = 0;


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.2f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        incrementFrames(delta);
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

    private void incrementFrames(float delta) {
        frameCounter += delta;

        if (frameCounter > 0.03) {
            frame = (frame + 1) % N_FRAMES;
            frameCounter = 0;
        }
    }
}
