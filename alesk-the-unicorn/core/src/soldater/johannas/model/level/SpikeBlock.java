package soldater.johannas.model.level;

public class SpikeBlock extends Block {
    public SpikeBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "spikeBlock";
    }
}