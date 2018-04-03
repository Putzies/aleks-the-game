package soldater.johannas.model;

public class Player extends Character {

    public static final int WIDTH = 132;
    public static final int HEIGHT = 105;
    public static final int STANDING = 0;
    public static final int RUNNING = 1;
    public static final int JUMPING = 2;


    private int state = 0;

    public Player(int x, int y) {
        super(x, y);
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
    public void setCollision(int collision, boolean value, double correction) {
        super.setCollision(collision, value, correction);
        if (collision == DOWN) {
            state = STANDING;
        }
    }

    @Override public void left () {
        super.left();

        if (state != JUMPING) {
            state = RUNNING;
        }
    }

    @Override public void right() {
        super.right();

        if (state != JUMPING){
            state = RUNNING;
        }
    }

    @Override public void jump() {
        super.jump();
        state = JUMPING;
    }

    @Override
    public void stop() {
        if (state != JUMPING) {
            state = STANDING;
        }
    }
}
