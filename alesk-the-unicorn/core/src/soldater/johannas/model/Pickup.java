package soldater.johannas.model;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Pickup implements Drawable, Entity {
    private final int UP = 1;
    private final int DOWN = -1;

    private final int Y_VEL_LIMIT = 40;
    private double BOUNCING_SPEED = 2;

    private double x,y;
    private double yVel = 0;
    private int dir = DOWN;

    // Task object used for disabling a pickup effect after a set time.
    protected TimerTask t;
    protected Timer taskTimer;

    public Pickup(){
        BOUNCING_SPEED += new Random().nextDouble() * 2;
    }

    public Pickup(double x, double y) {
        this();
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public int getDirection() {
        return 0;
    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public void update(double dTime) {
        yVel += BOUNCING_SPEED * dir;
        y += yVel * dTime;

        if (Math.abs(yVel) > Y_VEL_LIMIT) {
            dir = -dir;
        }
    }

    // Abstract method doing something on the Player
    public abstract void doIt(Player player);

    // Abstract method doing something on the world?.
    // Solves the lunchbox problem, adds further structure for adding other items. (Door keys etc).
    // public abstract void doIt(Game game);
}
