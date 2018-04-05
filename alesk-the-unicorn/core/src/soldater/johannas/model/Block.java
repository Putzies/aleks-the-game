package soldater.johannas.model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class Block implements Drawable {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    private HangingEnemy hangingEnemy;

    private final int X,Y;

    public Block(int x, int y) {
        X = x;
        Y = y;
    }

    public Block(int x, int y, boolean hasEnemy) {
        X = x;
        Y = y;
        if (hasEnemy) {
            hangingEnemy = new HangingEnemy(x, y-(HEIGHT)+10);
        }
    }

    @Override
    public double getX() {
        return X;
    }

    @Override
    public double getY() {
        return Y;
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
        return "block";
    }

    public HangingEnemy getHangingEnemy() {
        return hangingEnemy;
    }
}
