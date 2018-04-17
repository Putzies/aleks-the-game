package soldater.johannas.model;

public class EnergyDrink extends Pickup {

    // TODO Remember to double check the actual size of the EnergyDrink!
    public static final int WIDTH = 27;
    public static final int HEIGHT = 48;

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
        // TODO get the lunchbox spritename
        return "energydrink";
    }
}
