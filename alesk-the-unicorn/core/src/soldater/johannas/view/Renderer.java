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

    public final int NUM_FRAMES = 6;
    public final int FRAME_FREQ = 3;

    private int frameTimer = 0;


    private SpriteBatch batch;
    private Map<String, Texture> textures;

    private int playerFrame = 0;

    private Drawable player;
    private List<? extends Drawable> drawables;

    private int playerX;
    private int playerY;

    public Renderer(Drawable player, List<Drawable> drawables) {
        this.drawables = drawables;
        this.player = player;

        batch = new SpriteBatch();
        loadTextures();

        playerX = Gdx.graphics.getWidth() / 2 - player.getWidth() / 2;
        playerY = Gdx.graphics.getHeight() / 2 - player.getHeight() / 2;
    }

    public void render() {
        Gdx.gl.glClearColor(1, 0.8039f, 0.6667f, 1 + (float)(player.getY()));
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        Texture background = textures.get("background");

        int addFstBackground = ((int)(player.getX() * 0.25) / background.getWidth()) % 2;
        int addSndBackground = (((int)(player.getX() * 0.25) + background.getWidth()) / background.getWidth()) % 2;

        double playerOffsetFirstX = (-player.getX() * 0.25) % (background.getWidth() * 2);
        double playerOffsetSecondX = ((-player.getX() * 0.25) - background.getWidth()) % (background.getWidth() * 2);

        int bgX1 = (int)(playerOffsetFirstX + addFstBackground * background.getWidth() * 2);
        int bgX2 = (int)(playerOffsetSecondX + addSndBackground * background.getWidth() * 2);
        int bgY = (int)(((-player.getY())+player.getHeight()/2)*0.25);
        batch.draw(background, bgX1, bgY);
        batch.draw(background, bgX2, bgY);

        Texture sky = textures.get("sky");

        int addFirstSky = ((int)(player.getY() * 0.25) / sky.getHeight()) % 2;
        int addSecondSky = (((int)(player.getY() * 0.25) + sky.getHeight()) / sky.getHeight()) % 2;

        double playerOffsetFirstY = (-player.getY() * 0.25) % (sky.getHeight() * 2);
        double playerOffsetSecondY = ((-player.getY() * 0.25) - sky.getHeight()) % (sky.getHeight() * 2);

        int skyY1 = (int)(playerOffsetFirstY + addFirstSky * sky.getHeight() * 2);
        int skyY2 = (int)(playerOffsetSecondY + addSecondSky * sky.getHeight() * 2);

        if (player.getY() >= background.getHeight()*4) {
            batch.draw(sky, bgX1, skyY1);
            batch.draw(sky, bgX2, skyY1);
        }
        if (player.getY() >= 0) {
            batch.draw(sky, bgX1, skyY2);
            batch.draw(sky, bgX2, skyY2);
        }

        batch.draw(
                textures.get("player"),
                playerX,
                playerY,
                player.getWidth(),
                player.getHeight(), // This can be used for different animations!
                playerFrame * player.getWidth(),
                player.getState()*player.getHeight(),
                player.getWidth(),
                player.getHeight(),
                player.getDirection() == Drawable.LEFT,
                false
        );

        for (Drawable drawable : drawables) {
            batch.draw(
                    textures.get(drawable.getName()),
                    (int)(drawable.getX() - player.getX() + playerX),
                    (int)(drawable.getY() - player.getY() + playerY),
                    0,
                    0,
                    drawable.getWidth(),
                    drawable.getHeight()
            );
        }

        batch.end();

        incrementFrames();
    }

    public void dispose() {
        batch.dispose();
        disposeTextures();
    }

    private void loadTextures() {
        textures = new HashMap<String, Texture>();
        textures.put(player.getName(), new Texture(player.getName() + ".png"));
        textures.put("background", new Texture("background.png"));
        textures.put("sky", new Texture("starsky.png"));
        for (Drawable drawable : drawables) {
            textures.put(drawable.getName(), new Texture(drawable.getName() + ".png"));
        }
    }

    private void disposeTextures() {
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
    }

    private void incrementFrames() {
        frameTimer += 1;
        if (frameTimer > FRAME_FREQ) {
            playerFrame = (playerFrame + 1) % NUM_FRAMES;
            frameTimer = 0;
        }
    }
}
