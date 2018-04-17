package soldater.johannas.model.level;

import soldater.johannas.model.Drawable;

import java.util.List;

public class Platform implements Drawable{
    private final int width;
    private final int height;

    private final int X,Y;

    // For safety reasons, dont make the platforms larger than 10-15 blocks each.
    private final List<Block> blocks;

    public Platform(int x, int y, List<Block>blocks, int vertical, int horizontal) {
        X = x;
        Y = y;

        width  = horizontal * Block.WIDTH;
        height = vertical   * Block.HEIGHT;

        this.blocks = blocks;
    }

    @Override
    public double getX() {
        return X;
    }

    @Override
    public double getY() {
        return Y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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
    public String getName() {
        return "platform";
    }

    public List<Block>getBlocks(){
        return blocks;
    }
}
