package soldater.johannas.model.level.pickups;

import soldater.johannas.model.level.Level;

public class Lunchbox extends Pickup implements LevelPickup {
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
        return "lunchbox";
    }

    public void doIt(Level level){
     level.takenLunchboxes ++;
    }

}
