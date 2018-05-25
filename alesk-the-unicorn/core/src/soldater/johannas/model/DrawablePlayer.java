package soldater.johannas.model;

/**
 * Holds info about the player character required by the view
 */
public interface DrawablePlayer extends Drawable {

    int getLife();

    int getPickupState();
}
