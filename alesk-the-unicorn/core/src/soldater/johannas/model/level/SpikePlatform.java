package soldater.johannas.model.level;

import java.util.ArrayList;

public class SpikePlatform extends Platform {

    @Override
    public void construct() {
        blocks = new ArrayList<>();

        if (dir == VERTICAL) {
            for (int i = 0; i < length * GrassBlock.WIDTH; i += GrassBlock.WIDTH) {
                blocks.add(new SpikeBlock(X + i, Y));
            }

            WIDTH = Block.WIDTH * blocks.size();
            HEIGHT = Block.HEIGHT;
        } else {
            for (int i = 0; i < length * GrassBlock.HEIGHT; i += GrassBlock.HEIGHT) {
                blocks.add(new SpikeBlock(X, Y + i));
            }
            WIDTH = GrassBlock.WIDTH;
            HEIGHT = GrassBlock.HEIGHT * blocks.size();
        }
    }

    @Override
    public boolean isHarmful() {
        return true;
    }
}
