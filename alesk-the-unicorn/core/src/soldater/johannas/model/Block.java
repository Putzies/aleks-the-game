package soldater.johannas.model;

public class Block implements Drawable {

    private final int X,Y;

    public Block(int x, int y) {
        X = x;
        Y = y;
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public String getName() {
        return "block";
    }
}
