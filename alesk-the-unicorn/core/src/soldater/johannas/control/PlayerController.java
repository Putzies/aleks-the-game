package soldater.johannas.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import soldater.johannas.model.Movable;

public class PlayerController {
    private Movable player;

    private boolean rightPressed = false;
    private boolean leftPressed = false;
    private boolean spacePressed = false;

    public PlayerController(Movable player) {
        this.player = player;
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.right();
            rightPressed = true;
        } else if (rightPressed){
            player.stop();
            rightPressed = false;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.left();
            leftPressed = true;
        } else if (leftPressed) {
            player.stop();
            leftPressed = false;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.jump();
            spacePressed = true;
        } else if (spacePressed) {
            player.stop();
            spacePressed = false;

        }
    }
}
