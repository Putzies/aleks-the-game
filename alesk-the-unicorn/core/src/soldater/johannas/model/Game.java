package soldater.johannas.model;

import soldater.johannas.model.level.Block;
import soldater.johannas.model.level.Level;
import soldater.johannas.model.level.Parser;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;


import java.util.ArrayList;
import java.util.List;

public class Game implements Entity {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private Level level;

    private List<Entity> entities;
    private List<Drawable> drawables;
    private List<Character> characters;
    private List<HangingEnemy> hangingEnemies;

    private Player player;

    public Game() {
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
        allObjects.addAll(level.pickups);
        return allObjects;
    }

    // Helper method for generating a list of all the pickups
    public List<Pickup> getPickups(){
        List<Pickup> pickups = new ArrayList<>();

        pickups.addAll(level.pickups);
        return pickups;
    }


    public Movable getPlayer() {
        return level.player;
    }
    public Player getPlayer1(){return level.player;}
    @Override
    public void update(double dTime) {
        for (Character character : characters) {
            character.update(dTime);
        }

        collideCharacters();
        collidePickups();
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

    private  void collidePickups(){
        for (Pickup d : this.getPickups()){
                boolean withinX = getPlayer().getX() + getPlayer().getWidth() > d.getX() &&
                        getPlayer().getX() < d.getX() + d.getWidth();

                boolean withinY = getPlayer().getY() + getPlayer().getHeight() > d.getY() &&
                        getPlayer().getY() + 1 < d.getY() + d.getHeight();

                if (withinX && withinY){
                    if(d.getName().matches("wings")) {
                        System.out.println(d.getName());
                        this.getPlayer1().setPickup(Player.WINGS, true);
                    } else if(d.getName().matches("lunchbox")) {
                        System.out.println(d.getName());

                    } else if(d.getName().matches("baguette")){
                        System.out.println(d.getName());

                    } else if(d.getName().matches("energydrink")){
                        System.out.println(d.getName());

                    }
                }


        }
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

            for (Block block : level.blocks) {
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

    public List<WalkingEnemy> getWalkingEnemies() {
        return level.enemies;
    }

    public List<HangingEnemy> getHangingEnemies() {
        return hangingEnemies;
    }
}
