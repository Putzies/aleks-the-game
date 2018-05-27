package soldater.johannas.model.level.pickups;
import soldater.johannas.model.level.Player;

// A better name is welcomed with open arms, a hug and a gift.

/**
 * A pickup affecting the player only
 */
public interface PlayerPickup {
    void doIt(Player player);
}
