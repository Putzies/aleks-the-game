package soldater.johannas.model;

public interface Drawable {
    static final int LEFT = 0;
    static final int RIGHT = 1;

    double getX();
    double getY();
    int getWidth();
    int getHeight();
    int getDirection();
    String getName();
}
