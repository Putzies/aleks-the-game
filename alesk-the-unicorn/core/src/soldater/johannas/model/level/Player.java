package soldater.johannas.model.level;

import soldater.johannas.model.Character;
import soldater.johannas.model.Drawable;
import soldater.johannas.model.Movable;

public class Player extends Character implements Movable {

    public static final int WIDTH       = 132;
    public static final int HEIGHT      = 105;
    public static final int STANDING    = 0;
    public static final int RUNNING     = 1;
    public static final int JUMPING     = 2;
    public static final int FALLING     = 3;

    // For flying, or a state with wings.
    public static final int FLYING      = 4;

    // For pickups
    public static final int WINGS       = 0;
    public static final int BAGUETTE    = 1;
    public static final int ENERGYDRINK = 2;

    private int state = 0;

    // New booleans
    private final boolean[] pickups = {false,false,false};



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

                if (pickups[ENERGYDRINK]) {
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

                if (pickups[ENERGYDRINK]) {
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
        if (state != FALLING && state != JUMPING || this.pickups[WINGS]) {
            state = JUMPING;

            // There are actually TWO cases where we can trigger jumping, this because of resetCollision().
            // The second case is what was being triggered
                if (!collisions[UP] && collisions[DOWN] || !collisions[UP] && !collisions[DOWN]) {
                    yVel = 1000;
                } else {
                    //    System.out.println("The state was  " + collisions[UP] + " : " + collisions[DOWN]);
            }
        }

    }

    @Override
    public void stop() {
        if (state != JUMPING) {
            state = STANDING;
        }

        if (yVel > 0) {
            yVel *= 0.5f;
        }

        xVel = 0;
    }

    public void setPickup(int pickup, boolean value){

        this.pickups[pickup] = value;

        // This should probably be in some giant setState function instead of setPickup
        // Set state to Flying if wings & true otherwise set it to falling, which will be changed into the proper state
        // On the next tick.
        if (pickup == WINGS && value){
            state = FLYING;
        } else if (pickup == BAGUETTE && value){
            /* Do something here*/
        } else if (pickup == ENERGYDRINK && value){
            /* Do something here*/
        } else { state = FALLING; }


    }


    public boolean getPickup(int pickup){
        return this.pickups[pickup];
    }

    private void applyGravity() {
        yVel -= 25;
    }

}
