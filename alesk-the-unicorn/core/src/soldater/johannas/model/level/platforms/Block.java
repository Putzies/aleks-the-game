package soldater.johannas.model.level.platforms;

import soldater.johannas.model.Drawable;
import soldater.johannas.model.Positionable;

/**
 * Represents a single block in the game
 */
public abstract class Block extends Positionable implements Drawable {
    public static final int WIDTH = 54;
    public static final int HEIGHT = 51;


    public Block (double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public int getDirection() {
        return 0;
    }

    @Override
    public int getState() {
        return 0;
    }


    @Override
    public abstract String getName();
}
