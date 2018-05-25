package soldater.johannas.model;

import soldater.johannas.model.level.HangingEnemy;
import soldater.johannas.util.Timer;

import java.util.List;

/**
 * Holds info required by the view about the game
 */
public interface DrawableGame {
    List<Drawable> getDrawables();
    DrawablePlayer getPlayer();
    List<HangingEnemy> getHangingEnemies();
    int getTakenLunchBoxes();
    int getTotalLunchBoxes();
    Timer getTimer();
}
