package soldater.johannas.model.level;

import soldater.johannas.model.Character;
import soldater.johannas.model.Drawable;
import soldater.johannas.model.DrawablePlayer;
import soldater.johannas.model.Movable;

// Models the player character. Is controlled by user input through PlayerController
public class Player extends Character implements Movable, DrawablePlayer {

    public static final int WIDTH       = 132;
    public static final int HEIGHT      = 105;

    // States of the player character
    private int state = 0;
    public static final int STANDING    = 0;
    public static final int RUNNING     = 1;
    public static final int JUMPING     = 2;
    public static final int FALLING     = 3;


    // Pickup states of the player character
    private int pickupState = 0;
    public static final int NORMAL  = 0;
    public static final int FLY     = 1;
    public static final int STRONG  = 2;
    public static final int FAST    = 3;


    public Player() {
        super();
        life = 4;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public int getState() {
        return state;
    }

    public double getYvel() {
        return super.getYvel();
    }

    @Override
    public String getName() {
        return "player";
    }

    @Override
    public void update(double dTime) {
        if (!collisions[DOWN]) {
            applyGravity();
            if(yVel < 0) {
                state = FALLING;
            }
        }

        super.update(dTime);

        if(isOnGround()){
            this.xVel = 0;
            knockbacked = false;
        }
    }

    @Override
    public void setCollision(int collision, double correction) {
        super.setCollision(collision, correction);
        if (collision == DOWN) {
            state = STANDING;
        }
    }

    @Override
    public void left () {
        if (!knockbacked) {
            if (!collisions[Character.LEFT]) {
                xVel = -600;

                if (pickupState == FAST) {
                    xVel = -1500;
                }
            }
            direction = Drawable.LEFT;

            if (state != JUMPING && state != FALLING) {
                state = RUNNING;
            }
        }
    }

    @Override
    public void right() {
        if (!knockbacked) {
            if (!collisions[Character.RIGHT]) {
                xVel = 600;

                if (pickupState == FAST) {
                    xVel = 1500;
                }
            }
            direction = Drawable.RIGHT;

            if (state != JUMPING && state != FALLING) {
                state = RUNNING;
            }
        }
    }

    @Override
    public void jump() {
        // CHANGED here. First check the state, if we are in a falling or jumping state already, we cannot enemyJump. UNLESS
        // Wings.
        if (state != FALLING && state != JUMPING || pickupState == FLY) {
            state = JUMPING;

            // There are actually TWO cases where we can trigger jumping, this because of resetCollision().
            // The second case is what was being triggered
            if (!collisions[UP] && collisions[DOWN] || !collisions[UP] && !collisions[DOWN]) {
                yVel = 1000;
            }
        }

    }

    @Override
    public void stop() {
        if (state != JUMPING && state != FALLING) {
            state = STANDING;
        }

        if (yVel > 0) {
            yVel *= 0.5f;
        }

        xVel = 0;
    }

    public void setPickup(int pickup){
        pickupState = pickup;
    }

    private void applyGravity() {
        yVel -= 25;
    }

    @Override
    public int getPickupState() {
        return pickupState;
    }

}
