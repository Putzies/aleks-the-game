package soldater.johannas.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import soldater.johannas.model.WalkingEnemy;

import java.util.Random;

public class WalkingEnemyController implements Controller{
    private WalkingEnemy enemy;
    private boolean goingLeft = true;
    private Random random = new Random();


    /* Create a new Sound which uses the input .wav or .mp3 file
     * In case of error, the main problem seems to be sampling in the .wav file being wrong.
     *
     */
    Sound jmpSound  = Gdx.audio.newSound(Gdx.files.internal("sounds/jump_02.wav"));

    public WalkingEnemyController(WalkingEnemy enemy) {
        this.enemy = enemy;
    }

    public void update() {
        if (random.nextInt(200) > 198 && enemy.isOnGround()) {
            enemy.jump();

            // Just call the play method of the sound with the given volume
            System.out.println( enemy.getSoundVolume());
            jmpSound.play(enemy.getSoundVolume());
        }
        if ((enemy.getX() < enemy.getLeftBound() && goingLeft) || random.nextDouble() > 0.99 || enemy.collidesLeft()) {
            enemy.right();
            goingLeft = false;
        } else if ((enemy.getRightbound() < enemy.getX() && !goingLeft) || random.nextDouble() > 0.99 || enemy.collidesRight()){
            enemy.left();
            goingLeft = true;
        } else if(goingLeft) {
            enemy.left();
        } else if (!goingLeft) {
            enemy.right();
        }
    }
}
