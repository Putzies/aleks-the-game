package soldater.johannas.model.level;

import soldater.johannas.model.Drawable;

import java.util.List;

public class Platform implements Drawable{
    private final int WIDTH;
    private final int HEIGHT;

    private final int X,Y;

    // For safety reasons, dont make the platforms larger than 10-15 blocks each.
    private final List<Block> BLOCKS;

    public Platform(int x, int y, List<Block>blocks){
        X = x;
        Y = y;
        this.BLOCKS = blocks;

        WIDTH  = blocks.size()*Block.WIDTH;
        HEIGHT = Block.HEIGHT;
    }
    public Platform(int x, int y, List<Block>blocks, int vertical, int horizontal) {
        X = x;
        Y = y;

        WIDTH  = horizontal * Block.WIDTH;
        HEIGHT = vertical   * Block.HEIGHT;

        this.BLOCKS = blocks;
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
    public String getName() {
        return "platform";
    }

    public List<Block>getBlocks(){
        return BLOCKS;
    }
}
