package soldater.johannas.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.model.Drawable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer {
    private SpriteBatch batch;
    private Map<String, Texture> textures;
    private List<? extends Drawable> drawables;
    public static Sprite backgroundSprite;

    public Renderer(List<Drawable> drawables) {
        this.drawables = drawables;
    }

    public void init() {
        batch = new SpriteBatch();
        loadTextures();
        backgroundSprite = new Sprite(new Texture("background.png"));
    }

    public void render() {
        Gdx.gl.glClearColor(1, 1, 2, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        backgroundSprite.draw(batch);

        for (Drawable drawable : drawables) {
            batch.draw(textures.get(drawable.getName()), (int)drawable.getX(), (int)drawable.getY());
        }

        batch.end();
    }

    public void dispose() {
        batch.dispose();
        disposeTextures();
    }

    private void loadTextures() {
        textures = new HashMap<String, Texture>();
        for (Drawable drawable : drawables) {
            textures.put(drawable.getName(), new Texture(drawable.getName() + ".png"));
        }
    }

    private void disposeTextures() {
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
    }
}
