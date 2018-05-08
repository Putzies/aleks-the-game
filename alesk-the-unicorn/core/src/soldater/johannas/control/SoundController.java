package soldater.johannas.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import soldater.johannas.model.Game;

public class SoundController {
    private Sound enemyJump = Gdx.audio.newSound(Gdx.files.internal("sounds/jump_02.wav"));
    private Sound jump = Gdx.audio.newSound(Gdx.files.internal("sounds/jump_07.wav"));
    private Sound theme = Gdx.audio.newSound(Gdx.files.internal("sounds/Alesk Theme 2.wav"));
    private Sound eat  = Gdx.audio.newSound(Gdx.files.internal("sounds/eat.wav"));
    private Sound damage = Gdx.audio.newSound(Gdx.files.internal("sounds/damage.wav"));
    private Sound die = Gdx.audio.newSound(Gdx.files.internal("sounds/died.wav"));

    private int lastTakenLunchBoxes = 0;
    private int lastHealth = 4;

    private final int MAX_ENEMY_SOUND_DISTANCE = 1000;

    public void enemyJump(double distance) {
        float volume = calculateVolume(distance);

        if (volume > 0) {
            enemyJump.play(calculateVolume(distance));
        }
    }

    public void update(Game game) {
        if (game.getTakenLunchBoxes() > lastTakenLunchBoxes) {
            lastTakenLunchBoxes = game.getTakenLunchBoxes();
            eat.play(1, 1f, 0);
        }

        if (game.getPlayer().getLife() < lastHealth) {
            lastHealth = game.getPlayer().getLife();
            damage.play(1, 1.0f, 0);
        }

        if (game.getPlayer().getLife() == 0) {
            die.play();
        }
    }

    public void loopTheme() {
        theme.loop(0.5f);
    }

    public void stopTheme() {
        theme.stop();
    }

    public void jump() {
        jump.play();
    }

    private float calculateVolume(double dist) {
        return 1 - (float)Math.min(1, dist / MAX_ENEMY_SOUND_DISTANCE);
    }
}
