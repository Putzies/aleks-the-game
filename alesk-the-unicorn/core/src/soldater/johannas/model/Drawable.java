package soldater.johannas.model;

public interface Drawable {
    static final int LEFT = 0;
    static final int RIGHT = 1;

    double getX();
    double getY();
    double getYvel();
    double getXvel();
    int getWidth();
    int getHeight();
    int getDirection();
    int getState();
    String getName();
}
