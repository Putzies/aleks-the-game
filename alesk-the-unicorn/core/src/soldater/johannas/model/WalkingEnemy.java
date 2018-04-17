package soldater.johannas.model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class WalkingEnemy extends Character implements Movable {
    public static final int WIDTH = 93;
    public static final int HEIGHT = 36;

    private int leftBound;
    private int rightBound;

    // Temporary, used for optimising collision detection.
    protected Vector3 min;
    protected Vector3 max;
    protected BoundingBox bBox;


    public WalkingEnemy(int x, int y, int range) {
        super(x, y);

        this.rightBound = x + range;
        this.leftBound = x;
    }

    public WalkingEnemy() {
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
        return rightBound;
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
