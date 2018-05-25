package soldater.johannas.view.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import soldater.johannas.model.Drawable;
import soldater.johannas.model.DrawableGame;
import soldater.johannas.model.DrawablePlayer;
import soldater.johannas.model.level.HangingEnemy;
import soldater.johannas.model.level.Player;


import java.util.HashMap;
import java.util.Map;
import static soldater.johannas.model.level.Player.*;

//Renders the playable game
public class GameRenderer {

    public final int NUM_FRAMES = 6;
    public final int FRAME_FREQ = 3;

    private int frameTimer = 0;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    private Map<String, Texture> textures;

    private int playerFrame = 0;

    private DrawableGame game;
    private RainbowEmitter rainbowEmitter;

    private int playerX;
    private int playerY;

    public GameRenderer(DrawableGame game) {
        this.game = game;

        rainbowEmitter = new RainbowEmitter();

        batch = new SpriteBatch();

        loadTextures();
        shapeRenderer = new ShapeRenderer();

        playerX = Gdx.graphics.getWidth() / 2 - game.getPlayer().getWidth() / 2;
        playerY = Gdx.graphics.getHeight() / 2 - game.getPlayer().getHeight() / 2;
    }

    // Renders the playable game
    public void render() {
        Drawable player = game.getPlayer();

        Gdx.gl.glClearColor(1, 0.8039f, 0.6667f, 1 + (float)(player.getY()));
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        shapeRenderer.setAutoShapeType(true);
        shapeRenderer.begin();

        drawBackgrounds();
        drawShapes();
        drawRainbow();
        drawPlayer();
        drawDrawables();

        batch.end();
        shapeRenderer.end();

        incrementFrames();
    }

    // Frees up memory
    public void dispose() {
        batch.dispose();
        disposeTextures();
    }

    // Loads the sprites and background textures
    private void loadTextures() {
        textures = new HashMap<>();

        textures.put(game.getPlayer().getName(), new Texture(game.getPlayer().getName() + ".png"));
        textures.put("playerFlying", new Texture("playerFlying.png"));
        textures.put("playerStrong", new Texture("playerStrong.png"));

        textures.put("clouds", new Texture("cloudSky.png"));
        textures.put("stars", new Texture("starSky.png"));

        for (Drawable drawable : game.getDrawables()) {
            textures.put(drawable.getName(), new Texture(drawable.getName() + ".png"));
        }
    }


    // Disposes the textures in order to free up memory
    private void disposeTextures() {
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
    }

    // Draws the backgrounds in parallax
    private void drawBackgrounds() {
        Drawable player = game.getPlayer();
        Texture clouds = textures.get("clouds");
        Texture stars = textures.get("stars");

        // Calculate x-positions of backgrounds
        int bgX1 = calculate1stBgX((int)(player.getX() * 0.25), clouds.getWidth());
        int bgX2 = calculate2ndBgX((int)(player.getX() * 0.25), clouds.getWidth());

        // Calculate Y position of cloud backgrounds
        int cloudsY = (int)(((-player.getY())+player.getHeight()/2)*0.25);

        // Draw cloud backgrounds
        batch.draw(clouds, bgX1, cloudsY);
        batch.draw(clouds, bgX2, cloudsY);

        // Calculate Y positions of star backgrounds
        int starsY1 = calculate1stStarsY((int)(player.getY() * 0.25), stars.getHeight());
        int starsY2 = calculate2ndStarsY((int)(player.getY() * 0.25), stars.getHeight());

        // Draw star backgrounds
        if (player.getY() >= clouds.getHeight()*4) {
            batch.draw(stars, bgX1, starsY1);
            batch.draw(stars, bgX2, starsY1);
        }
        if (player.getY() >= 0) {
            batch.draw(stars, bgX1, starsY2);
            batch.draw(stars, bgX2, starsY2);
        }
    }

    // Calculates the X position of the first background
    private int calculate1stBgX(int scaledPlayerX, int bgWidth) {
        int addFstBackground = (scaledPlayerX / bgWidth) % 2;
        double playerOffsetFirstX = (-scaledPlayerX) % (bgWidth * 2);
        return (int)(playerOffsetFirstX + addFstBackground * bgWidth * 2);
    }

    // Calculates the X position of the second background
    private int calculate2ndBgX(int scaledPlayerX, int bgWidth) {
        int addSndBackground = ((scaledPlayerX + bgWidth) / bgWidth) % 2;
        double playerOffsetSecondX = ((-scaledPlayerX) - bgWidth) % (bgWidth * 2);
        return (int)(playerOffsetSecondX + addSndBackground * bgWidth * 2);
    }

    // Calculates the Y position of the first background added in top of base backgrounds
    private int calculate1stStarsY(int scaledPlayerY, int starsHeight) {
        int addFirstSky = (scaledPlayerY / starsHeight) % 2;
        double playerOffsetFirstY = (-scaledPlayerY) % (starsHeight * 2);
        return (int)(playerOffsetFirstY + addFirstSky * starsHeight * 2);
    }

    // Calculates the Y position of the second background added in top of base backgrounds
    private int calculate2ndStarsY(int scaledPlayerY, int starsHeight) {
        int addSecondSky = ((scaledPlayerY + starsHeight) / starsHeight) % 2;
        double playerOffsetSecondY = ((-scaledPlayerY) - starsHeight) % (starsHeight * 2);
        return (int)(playerOffsetSecondY + addSecondSky * starsHeight * 2);
    }

    /**
     * Draws the rainbow
     */
    private void drawRainbow() {
        DrawablePlayer player = game.getPlayer();

        // Updates the rainbow by adding rainbow particles based on player characters position
        rainbowEmitter.update(
                1,
                player.getX()+player.getWidth()/2,
                player.getY(),
                (player.getState() == FALLING || player.getState() == JUMPING || player.getPickupState() == Player.FAST)
        );

        // Draws the rainbow
        rainbowEmitter.draw(
                batch,
                playerX - (int)player.getX(),
                playerY - (int)player.getY(),
                player.getPickupState() == FAST
        );
    }

    /**
     * Draws the player character
     */
    private void drawPlayer() {
        DrawablePlayer player = game.getPlayer();

        // Determine which texture to use based on pickup states
        String textureStr = "player";
        switch(player.getPickupState()) {
            case FLY:
                textureStr += "Flying";
                break;
            case STRONG:
                textureStr += "Strong";
                break;
        }

        // Draw the player
        batch.draw(
                textures.get(textureStr),
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
    }

    /**
     * Draws the rest of the drawable entities of the game
     */
    private void drawDrawables() {
        Drawable player = game.getPlayer();

        for (Drawable drawable : game.getDrawables()) {
            if (textures.get(drawable.getName()).getWidth() == drawable.getWidth()) {

                // Draw all drawables that are not animated
                batch.draw(
                        textures.get(drawable.getName()),
                        (int)(drawable.getX() - player.getX() + playerX),
                        (int)(drawable.getY() - player.getY() + playerY)
                );
            } else {
                // Draw all drawables that are animated
                batch.draw(
                        textures.get(drawable.getName()),
                        (int)(drawable.getX() - player.getX() + playerX),
                        (int)(drawable.getY() - player.getY() + playerY),
                        textures.get(drawable.getName()).getWidth() == drawable.getWidth() ? 0 : playerFrame * drawable.getWidth(),
                        0,
                        drawable.getWidth(),
                        drawable.getHeight()
                );
            }
        }
    }

    /**
     * Draws the threads of the hanging spiders
     */
    private void drawShapes() {
        Drawable player = game.getPlayer();

        for(HangingEnemy hangingE : game.getHangingEnemies()) {
            float x = (float) (hangingE.getX() - player.getX() + playerX + hangingE.getWidth()/2);
            float y = (float) (hangingE.getY() - player.getY() + playerY + hangingE.getHeight() -5);
            float startY = (float) (hangingE.getStartY() - player.getY() + playerY + hangingE.getHeight());

            shapeRenderer.line(x, startY, x, y);
        }
    }

    /**
     * Increments the frames, in order to enable animation of sprites
     */
    private void incrementFrames() {
        frameTimer += 1;
        if (frameTimer > FRAME_FREQ) {
            playerFrame = (playerFrame + 1) % NUM_FRAMES;
            frameTimer = 0;
        }
    }
}
