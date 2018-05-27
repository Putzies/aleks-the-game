package soldater.johannas.model;

/**
 * Interface used for all updateable objects in the game
 */
public interface Entity extends Drawable {
    void update(double dTime);
}
