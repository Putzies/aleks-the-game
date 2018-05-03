package soldater.johannas.model.level.pickups;

import soldater.johannas.model.level.Player;
import soldater.johannas.model.level.pickups.Pickup;

import java.util.Timer;
import java.util.TimerTask;

public class EnergyDrink extends Pickup {

    // TODO Remember to double check the actual size of the EnergyDrink!
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
        // TODO get the lunchbox spritename
        return "energydrink";
    }

    @Override
    public void doIt(Player player){
        this.taskTimer = new Timer();
        player.setPickup(Player.ENERGYDRINK,true);

        this.t = new TimerTask() {
            @Override
            public void run() {
                player.setPickup(Player.ENERGYDRINK,false);
            }
        };

        taskTimer.schedule(t, 4000);
    }
}
