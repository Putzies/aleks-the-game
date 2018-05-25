package soldater.johannas.model;

/**
 * Holds info about objects required by the view
 */
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
