package soldater.johannas.model;

/**
 * Interface for controllers that moves characters
 */
public interface Movable extends Entity {
    void left();
    void right();
    void jump();
    void stop();
    double getYvel();
}
