package soldater.johannas.model.level;

public class LavaBlock extends Block {


    public LavaBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "lavaBlock";
    }
}
