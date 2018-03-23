package soldater.johannas.model;

public class Player extends Character {

    public static final int WIDTH = 132;
    public static final int HEIGHT = 93;

    public Player(int x, int y) {
        super(x, y);
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
    public String getName() {
        return "player";
    }
}
