package soldater.johannas.model.level.blocks;

import java.util.ArrayList;

public class GrassPlatform extends Platform {
    @Override
    public void construct() {
        blocks = new ArrayList<>();

        if (dir == VERTICAL) {
            for (int i = 0; i < length * GrassBlock.WIDTH; i += GrassBlock.WIDTH) {
                blocks.add(new GrassBlock(X + i, Y));
            }

            WIDTH = Block.WIDTH * blocks.size();
            HEIGHT = Block.HEIGHT;
        } else {
            for (int i = 0; i < length * GrassBlock.HEIGHT; i += GrassBlock.HEIGHT) {
                blocks.add(new GrassBlock(X, Y + i));
            }
            WIDTH = GrassBlock.WIDTH;
            HEIGHT = GrassBlock.HEIGHT * blocks.size();
        }
    }

    @Override
    public boolean isHarmful() {
        return false;
    }
}
