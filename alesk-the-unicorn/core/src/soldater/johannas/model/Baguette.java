package soldater.johannas.model;

public class Baguette extends Pickup {

    // TODO Remember to double check the actual size of the Lunchboxes!
    public static final int WIDTH = 51;
    public static final int HEIGHT = 39;

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
        // TODO get the baguette spritename
        return "baguette";
    }
}

