package soldater.johannas.model;

public class Player implements Entity, Movable {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 150;

    private double x = 100;
    private double y = 300;
    private double xVel, yVel;

    @Override
    public void update(double dTime) {
        if (y > 0) {
            applyGravity();
        } else {
            yVel = 0;
        }

        x += xVel * dTime;
        y += yVel * dTime;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return WIDTH;
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }

    @Override
    public String getName() {
        return "player";
    }

    @Override
    public void left() {
        x -= 6;
    }

    @Override
    public void right() {
        x += 6;
    }

    @Override
    public void jump() {
        y += 20;
        yVel = 40;
    }

    private void applyGravity() {
        yVel -= 3;
    }
}
