package soldater.johannas.model.level;

import soldater.johannas.model.Player;
import soldater.johannas.model.WalkingEnemy;

import java.util.List;

public class Level {
    public final List<Block> blocks;
    public final Player player;
    public final List<WalkingEnemy> enemies;

    public Level(Player player, List<Block> blocks, List<WalkingEnemy> enemies) {
        this.player = player;
        this.blocks = blocks;
        this.enemies = enemies;
    }
}
