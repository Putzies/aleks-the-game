package soldater.johannas.model.level.platforms;

import java.util.ArrayList;

/**
 * Represents a grass platform in the game
 */
public class GrassPlatform extends Platform {
    @Override
    public void construct() {
        blocks = new ArrayList<>();

        if (dir == VERTICAL) {
            for (int i = 0; i < length * Block.WIDTH; i += Block.WIDTH) {
                blocks.add(new GrassBlock(x + i, y));
            }

            WIDTH = Block.WIDTH * blocks.size();
            HEIGHT = Block.HEIGHT;
        } else {
            for (int i = 0; i < length * Block.HEIGHT; i += Block.HEIGHT) {
                blocks.add(new GrassBlock(x, y + i));
            }
            WIDTH = Block.WIDTH;
            HEIGHT = Block.HEIGHT * blocks.size();
        }
    }

    @Override
    public boolean isHarmful() {
        return false;
    }
}
