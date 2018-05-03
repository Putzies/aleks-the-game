package soldater.johannas.control;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import soldater.johannas.model.Movable;

public class PlayerController implements Controller{
    private Movable player;

    private boolean rightPressed = false;
    private boolean leftPressed = false;
    private boolean spacePressed = false;

    public PlayerController(Movable player) {
        this.player = player;
    }


    /* Create a new Sound which uses the input .wav or .mp3 file
     * In case of error, the main problem seems to be sampling in the .wav file being wrong.
     *
     */
    private Sound jmpSound  = Gdx.audio.newSound(Gdx.files.internal("sounds/jump_07.wav"));

    // Example of invalid .wav sampling
    // Sound jmpSound2 = Gdx.audio.newSound(Gdx.files.internal("mb_jump.wav"));

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

            // Using the sound is just one line of code, i.e calling it to play.
            jmpSound.play();

        } else if (spacePressed && player.getYvel() == 0) {
            player.stop();
            spacePressed = false;
        }
    }
}
