package soldater.johannas.model;

public abstract class Character implements Movable, Entity{
    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 3;

    private int offset = 0;
    private double x,y;
    private double xVel, yVel;

    private boolean[] collisions = {false, false, false, false};



    public Character(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(double dTime) {
        if (!collisions[DOWN]) {
            applyGravity();
        }

        x += xVel * dTime;
        y += yVel * dTime;
    }

    public void resetCollisions() {
        for (int i = 0; i < 4; i++) {
            collisions[i] = false;
        }
    }

    public void setCollision(int location, boolean value, double correction) {
        collisions[location] = value;

        if(value) {
            if (location == UP || location == DOWN) {
                y = correction;
                yVel = 0;
            } else {
                x = correction;
                xVel = 0;
            }
        }
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
        if (!collisions[LEFT]) {
            x -= 10;
        }
        offset = (offset + 132) % 792;

    }

    @Override
    public void right() {
        if (!collisions[RIGHT]) {
            x += 10;
        }
        offset = (offset + 132) % 792;
    }

    @Override
    public void jump() {
        if (!collisions[UP]) {
            yVel = 30;
        }
    }

    private void applyGravity() {
        yVel -= 0.8;
    }
}
