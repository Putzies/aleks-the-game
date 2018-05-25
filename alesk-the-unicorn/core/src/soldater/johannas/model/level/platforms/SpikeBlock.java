package soldater.johannas.model.level.platforms;

/**
 * Represents a spike block in the game
 */
public class SpikeBlock extends Block {
    public SpikeBlock(double x, double y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "spikeBlock";
    }
}
