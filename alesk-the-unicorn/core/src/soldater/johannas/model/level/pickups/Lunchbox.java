package soldater.johannas.model.level.pickups;

import soldater.johannas.model.level.Level;
import soldater.johannas.model.level.Player;

public class Lunchbox extends Pickup implements LevelPickup {
    // TODO Remember to double check the actual size of the Lunchboxes!
    public static final int WIDTH = 69;
    public static final int HEIGHT = 87;

    public Lunchbox() {
        super();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public String getName() {
        // TODO get the lunchbox spritename
        return "lunchbox";
    }

    public void doIt(Level level){
     level.takenLunchboxes ++;
    }

}
