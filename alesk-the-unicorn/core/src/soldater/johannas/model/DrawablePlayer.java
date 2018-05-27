package soldater.johannas.model;

/**
 * Holds some more info required by the view about the player character
 */
public interface DrawablePlayer extends Drawable {

    int getLife();

    int getPickupState();
}
