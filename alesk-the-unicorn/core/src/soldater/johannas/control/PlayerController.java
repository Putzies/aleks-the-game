package soldater.johannas.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import soldater.johannas.model.Movable;

public class PlayerController implements Controller{
    private Movable player;
    private SoundController soundController;

    private boolean rightPressed = false;
    private boolean leftPressed = false;
    private boolean spacePressed = false;

    public PlayerController(Movable player, SoundController soundController) {
        this.player = player;
        this.soundController = soundController;
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

            soundController.jump();

        } else if (spacePressed && player.getYvel() == 0) {
            player.stop();
            spacePressed = false;
        }
    }
}
