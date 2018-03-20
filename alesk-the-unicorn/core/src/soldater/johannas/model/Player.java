package soldater.johannas.model;

public class Player implements Entity {

    private int x;
    private int dir = 1;

    @Override
    public void update(double dTime) {
        if ((dir == 1 && x > 500) || (dir == -1 && x < 0)) {
            dir = -dir;
        }

        x += dir;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return (x * x) / 500;
    }
}
