package soldater.johannas.model;

public class Block implements Drawable {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    private final int X,Y;

    public Block(int x, int y) {
        X = x;
        Y = y;
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
    public int getOffset() {
        return 0;
    }

    @Override
    public String getName() {
        return "block";
    }
}
