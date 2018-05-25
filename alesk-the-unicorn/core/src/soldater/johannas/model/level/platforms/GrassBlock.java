package soldater.johannas.model.level.platforms;

/**
 * Represents a grass block in the game
 */
public class GrassBlock extends Block {

    public GrassBlock(double x, double y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "grassBlock";
    }
}
