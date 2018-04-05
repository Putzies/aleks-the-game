package soldater.johannas.model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

import java.util.ArrayList;
import java.util.List;

public class World implements Entity {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private List<Entity> entities;
    private List<Drawable> drawables;
    private List<Character> characters;
    private List<WalkingEnemy> walkingEnemies;
    List<HangingEnemy> hangingEnemies;
    private List<Block> blocks;

    private Player player;

    public World() {
        hangingEnemies = new ArrayList<HangingEnemy>();
        player = new Player(0, 200);
        blocks = new ArrayList<Block>();
        buildSomeExampleBlocks();

        walkingEnemies = new ArrayList<WalkingEnemy>();
        walkingEnemies.add(new WalkingEnemy(300, 300, 400));
        walkingEnemies.add(new WalkingEnemy(500, 100, 500));


        drawables = new ArrayList<Drawable>();
        drawables.addAll(walkingEnemies);
        drawables.addAll(hangingEnemies);

        entities = new ArrayList<Entity>();


        characters = new ArrayList<Character>();
        characters.add(player);
        characters.addAll(walkingEnemies);
        characters.addAll(hangingEnemies);
    }

    public List<Drawable> getDrawables() {
        List<Drawable> allObjects = new ArrayList<Drawable>();
        allObjects.addAll(drawables);
        allObjects.addAll(entities);
        allObjects.addAll(blocks);
        return allObjects;
    }

    public Movable getPlayer() {
        return player;
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

            /*
            Vector3 min = new Vector3(0,0,0);
            Vector3 max = new Vector3(10,10,0);

            Vector3 min2 = new Vector3(10,10,0);
            Vector3 max3 = new Vector3(30,30,0);

            BoundingBox bBox2 = new BoundingBox(min,max);
            BoundingBox bBox  = new BoundingBox(min2,max3);

            System.out.println(bBox.intersects(bBox2));

            */

            for (Block block : blocks) {
                // Checks if any point at all is intersecting, if not then we can ignore the rest of the statements


                    // Unneeded since we know they intersect already?
                    boolean withinX = character.getX() + character.getWidth() > block.getX() &&
                            character.getX() < block.getX() + block.getWidth();

                    boolean withinY = character.getY() + character.getHeight() > block.getY() &&
                            character.getY() + 1 < block.getY() + block.getHeight();


                    // DOWN
                    if (withinX &&
                            character.getY() + character.getHeight() > block.getY() + block.getHeight() &&
                            character.getY() < block.getY() + block.getHeight()) {

                        // For debugging purposes.
                        if (character instanceof Player) {
                            // System.out.println((character.getY() + character.getHeight()) + " " + (block.getY() + block.getHeight()) + " ::"
                            //       + character.getY() + " " + (block.getY() + block.getHeight() ));
                        }

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

                if (character instanceof Player) {
                    for (Character character1 : characters) {
                        if (character1 == character) {
                            continue;
                        }

                        boolean withinX = character.getX() + character.getWidth() > character1.getX() &&
                                    character.getX() < character1.getX() + character1.getWidth();

                        boolean withinY = character.getY() + character.getHeight() > character1.getY() &&
                                character.getY() + 1 < character1.getY() + character1.getHeight();

                        // Check if player and some character are colliding.
                        if (withinX && withinY) {

                            // Either we are facing Right or we are facing Left
                            if(character.getDirection() == Drawable.RIGHT ) {

                                character.yVel =  10;
                                character.xVel = -10;

                            } else {

                                character.yVel =  10;
                                character.xVel =  10;
                            }
                        }
                    }
                }
            }
        }

    // TODO: Remove :)
    private void buildSomeExampleBlocks() {

        // Floor
        for (int i = -10000; i < 10000; i += Block.WIDTH) {
            blocks.add(new Block(i, 0));
        }

        // Left wall
        for (int i = 0; i < 1000; i += Block.HEIGHT) {
            blocks.add(new Block(-10000 - Block.WIDTH, i));
        }

        // Right wall
        for (int i = 0; i < 1000; i += Block.HEIGHT) {
            blocks.add(new Block(10000 + Block.WIDTH, i));
        }

        // Test platform
        for (int i = 200; i < 1000; i += Block.HEIGHT) {
            blocks.add(new Block(i, 250));
        }

        // Test hangingEnemy
        Block block = new Block(150, 250, true);
        blocks.add(block);
        hangingEnemies.add(block.getHangingEnemy());
        Block block2 = new Block(100, 250, true);
        blocks.add(block2);
        hangingEnemies.add(block2.getHangingEnemy());


        // Test wall at platform
        for (int i = 250; i < 450; i += Block.HEIGHT) {
            blocks.add(new Block(1000, i));
        }
    }

    public List<WalkingEnemy> getWalkingEnemies() {
        return walkingEnemies;
    }

    public List<HangingEnemy> getHangingEnemies() {
        return hangingEnemies;
    }

}
