package soldater.johannas.model.level.blocks;

public class LavaBlock extends Block {


    public LavaBlock(int x, int y) {
        super(x, y);
    }


    public boolean isHarmful() {
        return true;
    }
    @Override
    public String getName() {
        return "lavaBlock";
    }
}
