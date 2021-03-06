package soldater.johannas.model.level.platforms;

import soldater.johannas.model.level.HangingEnemy;
import soldater.johannas.model.Positionable;

import java.util.List;

/**
 * Represents a generic platform in the game, i.e. a collection of blocks
 */
public abstract class Platform extends Positionable{
    public static final int VERTICAL = 0;

    protected int WIDTH;
    protected int HEIGHT;

    protected int length;
    protected int dir;

    private List<HangingEnemy> hangingEnemies;

    // For safety reasons, dont make the platforms larger than 10-15 platforms each.
    protected List<Block> blocks;

    public abstract void construct();

    // Decides weather a platform should give damage
    public abstract boolean isHarmful();

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public List<HangingEnemy> getHangingEnemies() {
        return hangingEnemies;
    }

    public List<Block>getBlocks(){
        return blocks;
    }
}
