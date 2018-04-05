package soldater.johannas.model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public abstract class Character implements Entity{
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 3;

    protected boolean[] collisions = {false, false, false, false};
    protected int direction = Drawable.RIGHT;

    protected double x,y;
    protected double xVel, yVel;

    protected Vector3 min;
    protected Vector3 max;
    protected BoundingBox bBox;

    public Character(int x, int y) {
        this.x = x;
        this.y = y;

    }

    public Character(int x, int y, Vector3 min, Vector3 max) {
        this.x = x;
        this.y = y;

        bBox = new BoundingBox(min,max);

    }

    @Override
    public void update(double dTime) {
        x += xVel * dTime;
        y += yVel * dTime;
    }

    public void resetCollisions() {
        for (int i = 0; i < 4; i++) {
            collisions[i] = false;
        }
    }

    public void setCollision(int location, boolean value, double correction) {
        collisions[location] = value;

        if(value) {
            if (location == UP || location == DOWN) {
                y = correction;
                yVel = 0;
            } else {
                x = correction;
                xVel = 0;
            }
        }
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

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

}
