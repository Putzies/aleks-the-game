package soldater.johannas.model;

public abstract class Character implements Movable, Entity{
    private int offset = 0;
    private double x,y;
    private double xVel, yVel;

    public Character(int x, int y) {
        this.x = x;
        this.y = y;
    }

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
    public int getOffset() {
        return offset;
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
