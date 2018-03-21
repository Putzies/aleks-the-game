package soldater.johannas.model;

public class Player implements Entity, Movable {

    public static final int WIDTH = 132;
    public static final int HEIGHT = 93;

    private int offset = 0;
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
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Override
    public String getName() {
        return "player";
    }

    @Override
    public void left() {
        x -= 6;
        offset = (offset + 132) % 792;

    }

    @Override
    public void right() {
        x += 6;
        offset = (offset + 132) % 792;
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
