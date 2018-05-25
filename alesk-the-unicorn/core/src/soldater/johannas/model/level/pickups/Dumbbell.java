package soldater.johannas.model.level.pickups;

import soldater.johannas.model.level.Player;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A dumbbell pickup
 */
import static soldater.johannas.model.level.Player.STRONG;

public class Dumbbell extends Pickup implements PlayerPickup {

    public static final int WIDTH = 51;
    public static final int HEIGHT = 39;

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
        return "dumbbell";
    }

    /**
     * Sets the player state to STRONG for a specific amount of time
     * @param player the player character
     */
    @Override
    public void doIt(Player player){
        this.taskTimer = new Timer();
        player.setPickup(STRONG);

        this.t = new TimerTask() {
            @Override
            public void run() {
                if(player.getPickupState() == STRONG) {
                    player.setPickup(Player.NORMAL);
                }
            }
        };

        // Tiden behöver fixas till
        taskTimer.schedule(t, 4000);
    }

    public void cancelTimout() {
        taskTimer.cancel();
    }
}
