package soldater.johannas.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.model.Drawable;

import java.util.List;

public class Renderer {
    private SpriteBatch batch;
    private Texture img;
    private List<? extends Drawable> drawables;

    public Renderer(List<? extends Drawable> drawables) {
        this.drawables = drawables;
    }

    public void init() {
        batch = new SpriteBatch();
        img = new Texture("unicorn.jpg");
    }

    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        for (Drawable drawable : drawables) {
            batch.draw(img, drawable.getX(), drawable.getY());
        }

        batch.end();
    }

    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
