package soldater.johannas.model;

public class Enemy implements Entity {
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
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public String getName() {
        return "enemy";
    }
}
