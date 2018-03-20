package soldater.johannas.model;

import java.util.ArrayList;
import java.util.List;

public class World implements Entity {
    private List<Entity> entities;

    public World() {
        entities = new ArrayList<Entity>();
        entities.add(new Player());
    }

    public List<? extends Drawable> getDrawables() {
        return entities;
    }

    @Override
    public void update(double dTime) {
        for (Entity entity : entities) {
            entity.update(dTime);
        }
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}
