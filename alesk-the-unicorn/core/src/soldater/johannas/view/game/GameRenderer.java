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

    public void dispose() {
        //batch.dispose();
        disposeTextures();
    }

    private void loadTextures() {
        textures = new HashMap<>();
        textures.put(game.getPlayer().getName(), new Texture(game.getPlayer().getName() + ".png"));
        textures.put("background", new Texture("background.png"));
        textures.put("sky", new Texture("starsky.png"));
        textures.put("playerFlying", new Texture("playerFlying.png"));
        textures.put("playerStrong", new Texture("playerStrong.png"));


        for (Drawable drawable : game.getDrawables()) {
            textures.put(drawable.getName(), new Texture(drawable.getName() + ".png"));
        }
    }

    private void disposeTextures() {
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
    }

    private void drawBackgrounds() {
        Texture background = textures.get("background");
        Drawable player = game.getPlayer();

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
    }



    private void drawRainbow() {
        DrawablePlayer player = game.getPlayer();
        rainbowEmitter.update(
                1,
                player.getX()+player.getWidth()/2,
                player.getY(),
                (player.getState() == FALLING || player.getState() == JUMPING || player.getPickupState() == Player.FAST)
        );

        rainbowEmitter.draw(batch, playerX - (int)player.getX(), playerY - (int)player.getY(), player.getPickupState() == FAST);
    }

    private void drawPlayer() {
        DrawablePlayer player = game.getPlayer();
        String textureStr = "player";
        switch(player.getPickupState()) {
            case FLY:
                textureStr += "Flying";
                break;
            case STRONG:
                textureStr += "Strong";
                break;
        }
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
        // Debugging collisions
//        shapeRenderer.setColor(Color.GREEN);
//        shapeRenderer.box(playerX,playerY,0,player.getWidth(),player.getHeight(),0);
//        shapeRenderer.setColor(Color.WHITE);
    }

    private void drawDrawables() {
        Drawable player = game.getPlayer();

        for (Drawable drawable : game.getDrawables()) {
            if (textures.get(drawable.getName()).getWidth() == drawable.getWidth()) {
                batch.draw(
                        textures.get(drawable.getName()),
                        (int)(drawable.getX() - player.getX() + playerX),
                        (int)(drawable.getY() - player.getY() + playerY)
                );
            } else {
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

    private void drawShapes() {
        Drawable player = game.getPlayer();
        for(HangingEnemy hangingE : game.getHangingEnemies()) {
            float x = (float) (hangingE.getX() - player.getX() + playerX + hangingE.getWidth()/2);
            float y = (float) (hangingE.getY() - player.getY() + playerY + hangingE.getHeight() -5);
            float startY = (float) (hangingE.getStartY() - player.getY() + playerY + hangingE.getHeight());

            shapeRenderer.line(x, startY, x, y);
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
