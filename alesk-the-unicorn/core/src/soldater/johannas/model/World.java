package soldater.johannas.model;

import java.util.ArrayList;
import java.util.List;

public class World implements Entity {
    private List<Entity> entities;
    private List<Drawable> drawables;

    public World() {
        entities = new ArrayList<Entity>();
        entities.add(new Player());

        drawables = new ArrayList<Drawable>();
        drawables.add(new Block(10, 500));
        drawables.add(new Block(800, 300));
    }

    public List<Drawable> getDrawables() {
        List<Drawable> allObjects = new ArrayList<Drawable>();
        allObjects.addAll(drawables);
        allObjects.addAll(entities);
        return allObjects;
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

    @Override
    public String getName() {
        return "world";
    }
}
