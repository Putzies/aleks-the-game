package soldater.johannas.model.level.pickups;

import soldater.johannas.model.level.Player;

import java.util.Timer;
import java.util.TimerTask;

import static soldater.johannas.model.level.Player.STRONG;

public class Baguette extends Pickup implements PlayerPickup {

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
        return "baguette";
    }
    
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

