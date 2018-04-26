package soldater.johannas.model.level;

import java.util.ArrayList;

public class LavaPlatform extends Platform{

    @Override
    public void construct() {
        blocks = new ArrayList<>();

        if (dir == VERTICAL) {
            for (int i = 0; i < length * Block.WIDTH; i += Block.WIDTH) {
                blocks.add(new LavaBlock(X + i, Y));
            }

            WIDTH = Block.WIDTH * blocks.size();
            HEIGHT = Block.HEIGHT;
        } else {
            for (int i = 0; i < length * Block.HEIGHT; i += Block.HEIGHT) {
                blocks.add(new LavaBlock(X, Y + i));
            }
            WIDTH = Block.WIDTH;
            HEIGHT = Block.HEIGHT * blocks.size();
        }
    }
}