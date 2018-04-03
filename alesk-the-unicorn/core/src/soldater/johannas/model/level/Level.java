package soldater.johannas.model.level;

import soldater.johannas.model.Enemy;
import soldater.johannas.model.Player;

import java.util.List;

public class Level {
    public final List<Block> blocks;
    public final Player player;
    public final List<Enemy> enemies;

    public Level(Player player, List<Block> blocks, List<Enemy> enemies) {
        this.player = player;
        this.blocks = blocks;
        this.enemies = enemies;
    }
}
