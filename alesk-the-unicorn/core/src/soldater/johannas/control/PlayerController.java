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
        spacePressed = Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.UP);
        leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);



        if (rightPressed) {
            player.right();
            System.out.println(rightPressed);
        } else if (!rightPressed){

            System.out.println(rightPressed);
        }

        if (leftPressed) {
            player.left();
        } else if (!leftPressed) {

        }

        if (spacePressed) {
            player.jump();

            soundController.jump();

        } else if (!spacePressed) {
            player.stop();

        }
    }
}
