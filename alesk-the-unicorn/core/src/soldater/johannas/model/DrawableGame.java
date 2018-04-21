package soldater.johannas.model;

import java.util.List;

public interface DrawableGame {
    List<Drawable> getDrawables();
    Character getPlayer();
    List<HangingEnemy> getHangingEnemies();
    int getTakenLunchBoxes();
    int getTotalLunchBoxes();
}
