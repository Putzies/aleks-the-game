package soldater.johannas.model.level;

public abstract class Positionable {
    protected double x,y;

    public Positionable(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Positionable(){};

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
