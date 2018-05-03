package soldater.johannas.model;

import soldater.johannas.model.level.HangingEnemy;
import soldater.johannas.util.Timer;

import java.util.List;

public interface DrawableGame {
    List<Drawable> getDrawables();
    Character getPlayer();
    List<HangingEnemy> getHangingEnemies();
    int getTakenLunchBoxes();
    int getTotalLunchBoxes();
    Timer getTimer();
}
