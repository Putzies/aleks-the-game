package soldater.johannas.model.level.pickups;

import soldater.johannas.model.level.Player;

import java.util.Timer;
import java.util.TimerTask;

public class Baguette extends Pickup implements PlayerPickup {

    // TODO Remember to double check the actual size of the Lunchboxes!
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
        // TODO get the baguette spritename
        return "baguette";
    }
    
    public void doIt(Player player){
        this.taskTimer = new Timer();
        player.setPickup(Player.BAGUETTE, true);

        this.t = new TimerTask() {
            @Override
            public void run() {
                player.setPickup(Player.BAGUETTE, false);
            }
        };

        // Tiden behöver fixas till
        taskTimer.schedule(t, 4000);
    }
}

