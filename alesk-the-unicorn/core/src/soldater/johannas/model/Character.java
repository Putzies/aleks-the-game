package soldater.johannas.model;

import soldater.johannas.model.level.Positionable;

import java.util.Timer;
import java.util.TimerTask;

public abstract class Character extends Positionable implements Entity {
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 3;

    protected final boolean[] collisions;

    protected int direction = Drawable.RIGHT;

    protected double xVel, yVel;
    protected int life;

    // The maximum interaction distance, used for sound and collision
    protected double max_dist = 800;

    // Boolean for disabling arrow key movement while being knockbacked.
    protected boolean knockbacked = false;

    // Boolean for disabling damage in short time intervals
    protected boolean damaged = false;

    // Mid points, used for calculating sound distance.
    // TODO use midpoints for collisions
    protected double midX,midY;

    public Character(int x, int y) {
        this();
        this.x = x;
        this.y = y;
        this.midX = x + getWidth() / 2;
        this.midY = y + getHeight() / 2;
    }

    public Character() {
        collisions = new boolean[]{false, false, false, false};
    }

    @Override
    public void update(double dTime) {
        x += xVel * dTime;
        y += yVel * dTime;

        if (y < -3000) {
            life = 0;
        }
    }

    public void resetCollisions() {
        for (int i = 0; i < 4; i++) {
            collisions[i] = false;
        }
    }

    public void setCollision(int location, double correction) {
        collisions[location] = true;

        if (location == UP || location == DOWN) {
            y = correction;
            yVel = 0;
        } else {
            x = correction;
            xVel = 0;
        }
    }

    public double getMidX(){ return midX;}

    public double getMidY(){ return midY;}

    public double getYvel() {
        return yVel;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isOnGround() {
        return collisions[DOWN];
    }

    public boolean collidesLeft() {
        return collisions[LEFT];
    }

    public boolean collidesRight() {
        return collisions[RIGHT];
    }


    public int getLife(){
        return life;
    }

    public void damage() {
        damage(false);
    }

    public void damageInverted() {
        damage(true);
    }

    private void damage(boolean inverted) {
        xVel = 300 * -direction;
        yVel = 300 * (inverted ? -1 : 1);

        if(!damaged) {
            life--;
        }

        knockbacked = true;
        damaged = true;

        // Set timer to not take damage again immediately
        TimerTask t = new TimerTask(){
            @Override
            public void run() {
                damaged = false;
            }
        };
        new Timer().schedule(t, 1000);
    }
}
