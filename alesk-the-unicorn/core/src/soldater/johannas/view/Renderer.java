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

    private Drawable player;
    private List<? extends Drawable> drawables;
    public static Sprite backgroundSprite;

    private int playerX;
    private int playerY;

    public Renderer(Drawable player, List<Drawable> drawables) {
        this.drawables = drawables;
        this.player = player;

        batch = new SpriteBatch();
        loadTextures();

        playerX = Gdx.graphics.getWidth() / 2 - player.getWidth() / 2;
        playerY = Gdx.graphics.getHeight() / 2 - player.getHeight() / 2;
        backgroundSprite = new Sprite(new Texture("background.png"));
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(backgroundSprite, (int)(-player.getX()/3 + playerX), 0);

        batch.draw(
                textures.get("player"),
                playerX,
                playerY,
                player.getOffset(),
                0, // This can be used for different animations!
                player.getWidth(),
                player.getHeight()
        );

        for (Drawable drawable : drawables) {
            batch.draw(
                    textures.get(drawable.getName()),
                    (int)(drawable.getX() - player.getX() + playerX),
                    (int)(drawable.getY() - player.getY() + playerY),
                    drawable.getOffset(),
                    0,
                    drawable.getWidth(),
                    drawable.getHeight()
            );
        }

        batch.end();
    }

    public void dispose() {
        batch.dispose();
        disposeTextures();
    }

    private void loadTextures() {
        textures = new HashMap<String, Texture>();
        textures.put(player.getName(), new Texture(player.getName() + ".png"));
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
