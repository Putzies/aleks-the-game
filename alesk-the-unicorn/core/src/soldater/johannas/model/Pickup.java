package soldater.johannas.model;

public abstract class Pickup implements Drawable {
    private double x,y;

    public Pickup(){ }

    public Pickup(double x, double y) {
        this.x = x;
        this.y = y;
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
    public int getDirection() {
        return 0;
    }

    @Override
    public int getState() {
        return 0;
    }
}
