package soldater.johannas.model;

public class Player extends Character {

    public static final int WIDTH = 132;
    public static final int HEIGHT = 93;
    public static final int STANDING = 0;
    public static final int RUNNING = 1;

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

    @Override
    public String getName() {
        return "player";
    }

    @Override public void left () {
        super.left();
        state = RUNNING;
    }

    @Override public void right() {
        super.right();
        state = RUNNING;
    }

    @Override
    public void stop() {
        state = STANDING;
    }
}
