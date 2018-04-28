package soldater.johannas.model.level;

import soldater.johannas.model.Drawable;
import soldater.johannas.model.HangingEnemy;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class GrassBlock extends Block {

    public GrassBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public String getName() {
        return "grassBlock";
    }
}
