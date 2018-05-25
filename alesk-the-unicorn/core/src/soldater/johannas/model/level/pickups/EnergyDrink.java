package soldater.johannas.model.level.pickups;

import soldater.johannas.model.level.Player;

import java.util.Timer;
import java.util.TimerTask;

import static soldater.johannas.model.level.Player.FAST;

public class EnergyDrink extends Pickup implements PlayerPickup {

    public static final int WIDTH = 27;
    public static final int HEIGHT = 48;

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
        return "energydrink";
    }

    public void doIt(Player player){
        this.taskTimer = new Timer();
        player.setPickup(FAST);

        this.t = new TimerTask() {
            @Override
            public void run() {
                if (player.getPickupState() == FAST) {
                    player.setPickup(Player.NORMAL);
                }
            }
        };

        taskTimer.schedule(t, 4000);
    }
}
