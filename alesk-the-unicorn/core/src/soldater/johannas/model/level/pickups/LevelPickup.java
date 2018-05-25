package soldater.johannas.model.level.pickups;
import soldater.johannas.model.level.Level;

// A better name is welcomed with open arms, a hug and a gift.

/**
 * A pickup affecting the level
 */
public interface LevelPickup {
    void doIt(Level level);
}
