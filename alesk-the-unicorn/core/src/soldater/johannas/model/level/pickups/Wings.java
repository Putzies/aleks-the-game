package soldater.johannas.model.level.pickups;

import soldater.johannas.model.level.Player;

import java.util.Timer;
import java.util.TimerTask;

import static soldater.johannas.model.level.Player.FLY;

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
