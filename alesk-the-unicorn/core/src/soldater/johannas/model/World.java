package soldater.johannas.model;

import soldater.johannas.model.level.Block;
import soldater.johannas.model.level.Level;
import soldater.johannas.model.level.Parser;

import java.util.ArrayList;
import java.util.List;

public class World implements Entity {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private Level level;

    private List<Entity> entities;
    private List<Drawable> drawables;
    private List<Character> characters;
    private List<HangingEnemy> hangingEnemies;

    private Player player;

    public World() {
        drawables = new ArrayList<>();
        entities = new ArrayList<>();
        characters = new ArrayList<>();
        hangingEnemies = new ArrayList<>();
    }

    public boolean startGame(String levelName) {
        level = new Parser().loadLevel(levelName);
        characters.addAll(level.enemies);
        characters.add(level.player);

        level.blocks.stream().filter(b -> b.getHangingEnemy() != null).forEach(b -> hangingEnemies.add(b.getHangingEnemy()));

        return level != null;
    }

    public List<Drawable> getDrawables() {
        List<Drawable> allObjects = new ArrayList<Drawable>();
        allObjects.addAll(drawables);
        allObjects.addAll(entities);
        allObjects.addAll(level.blocks);
        allObjects.addAll(level.enemies);
        allObjects.addAll(hangingEnemies);
        return allObjects;
    }

    public Movable getPlayer() {
        return level.player;
    }

    @Override
    public void update(double dTime) {
        for (Character character : characters) {
            character.update(dTime);
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
    public int getDirection() {
        return 0;
    }

    @Override
    public int getState() {
        return 0;
    }

    @Override
    public String getName() {
        return "world";
    }

    private void collideCharacters() {
        for(Character character : characters) {
            character.resetCollisions();

            for (Block block : level.blocks) {

                boolean withinX = character.getX() + character.getWidth() > block.getX() &&
                            character.getX() < block.getX() + block.getWidth();

                boolean withinY = character.getY() + character.getHeight() > block.getY() &&
                            character.getY() + 1 < block.getY() + block.getHeight();


                // DOWN
                if (withinX &&
                        character.getY() + character.getHeight() > block.getY() + block.getHeight() &&
                        character.getY() < block.getY() + block.getHeight()) {
                    character.setCollision(Character.DOWN, true, block.getY() + block.getHeight() - 1);
                }

                // TOP
                if (withinX &&
                        character.getY() < block.getY() &&
                        character.getY() + character.getHeight() > block.getY()) {
                    character.setCollision(Character.UP, true, block.getY() - character.getHeight());
                }

                // RIGHT
                if (withinY &&
                        character.getX() < block.getX() &&
                        character.getX() + character.getWidth() > block.getX()) {
                    character.setCollision(Character.RIGHT, true, block.getX() - character.getWidth());
                }

                // LEFT
                if (withinY &&
                        character.getX() + character.getWidth() > block.getX() + block.getWidth() &&
                        character.getX() < block.getX() + block.getWidth()) {
                    character.setCollision(Character.LEFT, true, block.getX());
                }
            }
        }
    }

    public List<WalkingEnemy> getWalkingEnemies() {
        return level.enemies;
    }

    public List<HangingEnemy> getHangingEnemies() {
        return hangingEnemies;
    }
}
