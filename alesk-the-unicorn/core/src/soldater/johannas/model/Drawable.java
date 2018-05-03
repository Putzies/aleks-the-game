package soldater.johannas.model;

public interface Drawable {
    int LEFT = -1;
    int RIGHT = 1;

    double getX();
    double getY();
    int getWidth();
    int getHeight();
    int getDirection();
    int getState();
    String getName();
}
