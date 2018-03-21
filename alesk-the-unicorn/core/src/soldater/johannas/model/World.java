package soldater.johannas.model;

import java.util.ArrayList;
import java.util.List;

public class World implements Entity {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    private List<Entity> entities;
    private List<Drawable> drawables;
    private List<Character> characters;

    private Player player;

    public World() {
        player = new Player(50, 200);

        entities = new ArrayList<Entity>();

        drawables = new ArrayList<Drawable>();
        drawables.add(new Block(10, 500));
        drawables.add(new Block(800, 300));
        drawables.add(new Enemy(200, 200));

        characters = new ArrayList<Character>();
        characters.add(player);
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
        player.update(dTime);

        for (Entity entity : entities) {
            entity.update(dTime);
        }

        collideCharacters();
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
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public int getOffset() {
        return 0;
    }

    @Override
    public String getName() {
        return "world";
    }

    private void collideCharacters() {

    }
}
