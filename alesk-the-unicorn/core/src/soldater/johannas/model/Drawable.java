package soldater.johannas.model;

/**
 * Provides the only info required by the view about objects
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
