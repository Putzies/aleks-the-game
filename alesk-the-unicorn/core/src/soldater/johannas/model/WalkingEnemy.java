package soldater.johannas.model;

public class WalkingEnemy extends Character implements Movable {
    public static final int WIDTH = 93;
    public static final int HEIGHT = 36;

    private int leftBound;
    private int rightbound;

    public WalkingEnemy(int x, int y, int range) {
        super(x, y);
        this.leftBound = x;
        this.rightbound = x + range;
    }

    public Enemy() {
        super();
    }

    @Override
    public void update(double dTime) {
        if (!collisions[DOWN]) {
            applyGravity();
        }

        super.update(dTime);
    }

    @Override
    public void left() {
        if (!collisions[super.LEFT]) {
            x -= 2;
        }
        direction = Drawable.LEFT;
    }

    @Override
    public void right() {
        if (!collisions[super.RIGHT]) {
            x += 2;
        }
        direction = Drawable.RIGHT;
    }

    @Override
    public void jump() {
        if (!collisions[UP]) {
            yVel = 18;
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
    private void applyGravity() {
        yVel -= 2.2;
    }
}
