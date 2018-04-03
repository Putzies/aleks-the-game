package soldater.johannas.model;

public class Enemy extends Character {
    public static final int WIDTH = 93;
    public static final int HEIGHT = 36;

    private int leftBound;
    private int rightbound;

    public Enemy(int x, int y, int leftBound, int rightbound) {
        super(x, y);
        this.leftBound = leftBound;
        this.rightbound = rightbound;
    }

    @Override
    public void left() {
        if (!collisions[LEFT]) {
            x -= 2;
        }
        direction = Drawable.LEFT;

    }

    @Override
    public void right() {
        if (!collisions[RIGHT]) {
            x += 2;
        }
        direction = Drawable.RIGHT;
    }

    @Override
    public void jump() {
        if (!collisions[UP]) {
            yVel = 12;
        }
    }

    public int getLeftBound() {
        return leftBound;
    }

    public int getRightbound() {
        return rightbound;
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
    public int getDirection() {
        return 0;
    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public String getName() {
        return "enemy";
    }

    @Override
    public void stop() {
    }
}
