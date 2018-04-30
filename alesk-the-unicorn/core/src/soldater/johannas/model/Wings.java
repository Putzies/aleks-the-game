package soldater.johannas.model;

import java.util.Timer;
import java.util.TimerTask;

public class Wings extends Pickup {

    // TODO Remember to double check the actual size of the Wings!
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
        // TODO get the Wings spritename
        return "wings";
    }

    @Override
    public void doIt(Player player){
        this.taskTimer = new Timer();
        player.setPickup(Player.WINGS,true);

        this.t = new TimerTask() {
            @Override
            public void run() {
                player.setPickup(Player.WINGS,false);
            }
        };

        // Length might need some further modification.
        taskTimer.schedule(t, 4000);
    }
}
