package soldater.johannas.model;

public interface Movable extends Entity {
    void left();
    void right();
    void jump();
    void stop();
    double getYvel();
}
