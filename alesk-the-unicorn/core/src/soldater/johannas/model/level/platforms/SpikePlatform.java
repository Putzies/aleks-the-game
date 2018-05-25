package soldater.johannas.model.level.platforms;

import java.util.ArrayList;

/**
 * Represents a spike platform in the game
 */
public class SpikePlatform extends Platform {

    @Override
    public void construct() {
        blocks = new ArrayList<>();

        if (dir == VERTICAL) {
            for (int i = 0; i < length * Block.WIDTH; i += Block.WIDTH) {
                blocks.add(new SpikeBlock(x + i, y));
            }

            WIDTH = Block.WIDTH * blocks.size();
            HEIGHT = Block.HEIGHT;
        } else {
            for (int i = 0; i < length * Block.HEIGHT; i += Block.HEIGHT) {
                blocks.add(new SpikeBlock(x, y + i));
            }
            WIDTH = Block.WIDTH;
            HEIGHT = Block.HEIGHT * blocks.size();
        }
    }

    @Override
    public boolean isHarmful() {
        return true;
    }
}
