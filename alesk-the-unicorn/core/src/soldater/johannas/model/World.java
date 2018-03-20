package soldater.johannas.model;

import java.util.ArrayList;
import java.util.List;

public class World implements Entity {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    private List<Entity> entities;
    private List<Drawable> drawables;

    private Player player;

    public World() {
        player = new Player();

        entities = new ArrayList<Entity>();
        entities.add(player);

        drawables = new ArrayList<Drawable>();
        drawables.add(new Block(10, 500));
        drawables.add(new Block(800, 300));
        drawables.add(new Enemy(200, 200));
    }

    public List<Drawable> getDrawables() {
        List<Drawable> allObjects = new ArrayList<Drawable>();
        allObjects.addAll(drawables);
        allObjects.addAll(entities);
        return allObjects;
    }

    public Movable getPlayer() {
        return player;
    }

    @Override
    public void update(double dTime) {
        for (Entity entity : entities) {
            entity.update(dTime);
        }
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public double getWidth() {
        return WIDTH;
    }

    @Override
    public double getHeight() {
        return HEIGHT;
    }

    @Override
    public String getName() {
        return "world";
    }
}
