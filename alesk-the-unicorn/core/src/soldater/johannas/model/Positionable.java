package soldater.johannas.model;

/**
 * Class realized by all objects that has positions in the game
 */
public abstract class Positionable {
    protected double x,y;

    public Positionable(){}

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
