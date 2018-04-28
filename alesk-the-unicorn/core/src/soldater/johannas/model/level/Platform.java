package soldater.johannas.model.level;

import soldater.johannas.model.Drawable;
import soldater.johannas.model.HangingEnemy;

import java.util.List;

public abstract class Platform {
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    protected int WIDTH;
    protected int HEIGHT;

    protected int length;
    protected int dir;

    protected int X;
    protected int Y;

    private List<HangingEnemy> hangingEnemies;

    // For safety reasons, dont make the platforms larger than 10-15 platforms each.
    protected List<Block> blocks;

    public abstract void construct();

    public abstract boolean isHarmful();

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

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
