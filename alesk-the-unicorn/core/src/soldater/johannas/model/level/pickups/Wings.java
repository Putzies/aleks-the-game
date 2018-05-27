package soldater.johannas.model.level.pickups;

import soldater.johannas.model.level.Player;

import java.util.Timer;
import java.util.TimerTask;

import static soldater.johannas.model.level.Player.FLY;

/**
 * A pickup in the form of a pair of wings, allowing the player to fly for a short while
 */
public class Wings extends Pickup implements PlayerPickup {

    public static final int WIDTH = 75;
    public static final int HEIGHT = 66;

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
        return "wings";
    }

    /**
     * Sets the player state to FLY for a specific amount of time
     * @param player the player character
     */
    @Override
    public void doIt(Player player){
        this.taskTimer = new Timer();
        player.setPickup(FLY);

        this.t = new TimerTask() {
            @Override
            public void run() {
                if (player.getPickupState() == FLY) {
                    player.setPickup(Player.NORMAL);
                }
            }
        };

        // Length might need some further modification.
        taskTimer.schedule(t, 4000);
    }
}
