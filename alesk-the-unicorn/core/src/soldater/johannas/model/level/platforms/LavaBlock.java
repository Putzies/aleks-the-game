package soldater.johannas.model.level.platforms;

public class LavaBlock extends Block {


    public LavaBlock(double x, double y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "lavaBlock";
    }
}
