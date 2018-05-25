package soldater.johannas.model;

/**
 * Holds info required by the view about the player character
 */
public interface DrawablePlayer extends Drawable {

    int getLife();

    int getPickupState();
}
