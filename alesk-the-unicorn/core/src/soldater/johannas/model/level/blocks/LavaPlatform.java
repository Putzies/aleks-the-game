package soldater.johannas.model.level.blocks;

import java.util.ArrayList;

public class LavaPlatform extends Platform {

    @Override
    public void construct() {
        blocks = new ArrayList<>();

        if (dir == VERTICAL) {
            for (int i = 0; i < length * GrassBlock.WIDTH; i += GrassBlock.WIDTH) {
                blocks.add(new LavaBlock(X + i, Y));
            }

            WIDTH = GrassBlock.WIDTH * blocks.size();
            HEIGHT = GrassBlock.HEIGHT;
        } else {
            for (int i = 0; i < length * GrassBlock.HEIGHT; i += GrassBlock.HEIGHT) {
                blocks.add(new LavaBlock(X, Y + i));
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
