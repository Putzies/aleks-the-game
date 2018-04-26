package soldater.johannas.model.level;

import soldater.johannas.model.Drawable;
import soldater.johannas.model.HangingEnemy;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class Block implements Drawable {

    public static final int WIDTH = 54;
    public static final int HEIGHT = 51;

    private final int X,Y;

    public Block(int x, int y) {
        X = x;
        Y = y;
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
}
