package soldater.johannas.model;

public class Enemy implements Entity {
    public static final int WIDTH = 92;
    public static final int HEIGHT = 61;

    private double x;
    private double y;

    public Enemy (double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(double dTime) {

    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
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
    public String getName() {
        return "enemy";
    }
}
