package soldater.johannas.model.level.platforms;

/**
 * Represents a block in the game
 */
public class LavaBlock extends Block {

    public LavaBlock(double x, double y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "lavaBlock";
    }
}
