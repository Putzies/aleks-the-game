package soldater.johannas.model;

import com.badlogic.gdx.utils.Timer;
import soldater.johannas.model.level.Block;
import soldater.johannas.model.level.Level;
import soldater.johannas.model.level.Parser;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import soldater.johannas.model.level.Platform;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game implements Entity, DrawableGame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private Level level;

    private List<Entity> entities;
    private List<Character> characters;
    private List<HangingEnemy> hangingEnemies;

    private Player player;
    private Platform platform;


    // Task object used for disabling a pickup effect after a set time.
    Timer.Task t;

    public Game() {
        entities = new ArrayList<>();
        characters = new ArrayList<>();
        hangingEnemies = new ArrayList<>();
    }

    public boolean startGame(String levelName) {
        level = new Parser().loadLevel(levelName);
        characters.addAll(level.enemies);

        entities.addAll(characters);
        entities.addAll(level.pickups);
        characters.add(level.player);

        level.blocks.stream().filter(b -> b.getHangingEnemy() != null).forEach(b -> hangingEnemies.add(b.getHangingEnemy()));

        // Testing platform
        List<Block> quickList = new ArrayList<>();

        for (Block b : level.blocks) {
            if (b.getX() >= 0 && b.getY() + b.getHeight() <= 50) {
                quickList.add(b);

            }
        }
        platform = new Platform(0, 0, quickList, 1, 1 * quickList.size());

        return level != null;
    }

    @Override
    public List<Drawable> getDrawables() {
        List<Drawable> allObjects = new ArrayList<>();
        allObjects.addAll(level.blocks);
        allObjects.addAll(level.enemies);
        allObjects.addAll(hangingEnemies);
        allObjects.addAll(level.pickups);
        return allObjects;
    }

    @Override
    public Player getPlayer() {
        return level.player;
    }

    @Override
    public void update(double dTime) {
        for (Entity entity : entities) {
            entity.update(dTime);
        }

        level.player.update(dTime);

        calculateNewSoundVolumes();

        // Ordering here is important. Characters before Terrain will cause bugs.
        collideTerrain();
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

    // Collision code for pickups, sets a flag which triggers the said effect on the proper entity.
    private void collidePickups() {
        for (Iterator<Pickup> iterator = level.pickups.iterator(); iterator.hasNext();) {
            Pickup pickup = iterator.next();

            boolean withinX = isWithinX(getPlayer(),pickup);
            boolean withinY = isWithinY(getPlayer(),pickup);

            boolean remove = true;

            // Detecting a collision is simple
            // TODO Remove the pickup from the list
            if (withinX && withinY) {
                if (pickup.getName().matches("wings")) {

                    // Trigger the Wings flag, put a timer task for 4 seconds
                    level.player.setPickup(Player.WINGS, true);

                    // Schedule the disabling of the powerup sometime soon.
                    Timer.schedule(generateTask(Player.WINGS, false), 4);

                } else if (pickup.getName().matches("lunchbox")) {
                    // TODO Define behavior for picking up a luncbhox.

                } else if (pickup.getName().matches("baguette")) {

                    // Trigger the Baguette flag, put a timer task for 4 seconds
                    level.player.setPickup(Player.BAGUETTE, true);

                    // Schedule the disabling of the powerup sometime soon.
                    Timer.schedule(generateTask(Player.BAGUETTE, false), 4);

                } else if (pickup.getName().matches("energydrink")) {

                    // Trigger the Energydrink flag, put a timer task for 4 seconds
                    level.player.setPickup(Player.ENERGYDRINK, true);

                    // Schedule the disabling of the powerup sometime soon.
                    Timer.schedule(generateTask(Player.ENERGYDRINK, false), 4);
                } else {
                    remove = false;
                }

                if (remove) {
                    iterator.remove();
                }
            }
        }
    }

    // Collision code for Player versus some Character.
    // Future expansion: Collide against hanging spiders, which for some reason are not in characters.
    private void collideCharacters() {
        // We will constantly be using player. So lets store it for the time being.
        Player player = level.player;

        for (Character character : characters) {
            if (character == player) { continue; }

            boolean withinX = isWithinX(player, character);
            boolean withinY = isWithinY(player, character);

            // Check if player and some character are colliding.
            if (withinX && withinY) {
                player.knockbacked = true;

                // Either we are facing Right or we are facing Left
                if (player.getDirection() == Drawable.RIGHT) {
                    player.yVel = 300;
                    player.xVel = -300;

                } else {
                    player.yVel = 300;
                    player.xVel = 300;
                }
            }
        }

    }

    private void collideTerrain(){
/*
        getPlayer1().resetCollisions();
        // On each collision, generate two specific bounding boxes. 0 for platform, 1 for player
            /* TODO, Either move bounding box to character ("I know where i can collide with something") or
               TODO  Let the game know and create all the bounding boxes ("The world knows when something collides")

        BoundingBox bBox = generateConnected(0);
        BoundingBox pBox = generateConnected(1);

        if (pBox.intersects(bBox)){
            // DOWN
            if (getPlayer1().getY() + getPlayer1().getHeight() > platform.getY() + platform.getHeight() &&
                    getPlayer1().getY() < platform.getY() + platform.getHeight()) {
                getPlayer1().setCollision(Character.DOWN, true, platform.getY() + platform.getHeight() - 1);
            }
            // TODO expand here with else if for every other case possible. Not IF, because we can only have one point of impact.

        }
        */
        for (Character character : characters) {
            character.resetCollisions();

            for (Block block : level.blocks) {
                // Checks if any point at all is intersecting, if not then we can ignore the rest of the statements
                boolean withinX = isWithinX(character,block);
                boolean withinY = isWithinY(character,block);

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

    @Override
    public List<HangingEnemy> getHangingEnemies() {
        return hangingEnemies;
    }

    // Generate the appropriate Task given an n and the truth value.
    private Timer.Task generateTask(int n, boolean val) {
        Timer.Task task = new Timer.Task() {
            @Override
            public void run() {
                level.player.setPickup(n, val);
            }
        };

        return task;
    }

    /* Ugly for now as we are checking against the drawable interface.
     * Affector for the drawable that is moving
     * Affected for the drawable that we might collide against
     */
    private boolean isWithinX(Drawable affector, Drawable affected) {
        boolean withinX = affector.getX() + affector.getWidth() > affected.getX() &&
                affector.getX() < affected.getX() + affected.getWidth();

        return withinX;
    }

    private boolean isWithinY(Drawable affector, Drawable affected) {
        boolean withinY = affector.getY() + affector.getHeight() > affected.getY() &&
                affector.getY() + 1 < affected.getY() + affected.getHeight();

        return withinY;
    }

    //Just testing, generates a bounding box for either player or plattform
    private BoundingBox generateConnected(int n) {
        Vector3 min;
        Vector3 max;
        if (n == 0) {
            min = new Vector3((float) platform.getX(), (float) platform.getY(), 0);
            max = new Vector3((float) platform.getX() + platform.getWidth(), (float) platform.getY() + platform.getHeight(), 0);

        } else {
            min = new Vector3((float) level.player.getX(), (float) level.player.getY(), 0);
            max = new Vector3((float) level.player.getX() + level.player.getWidth(), (float) level.player.getY() + level.player.getHeight(), 0);

        }
        BoundingBox bBox2 = new BoundingBox(min, max);

        return bBox2;
    }

    // Ugly, semi-hard coded version.
    private void calculateNewSoundVolumes(){
        for (Character character : characters) {
            if (character instanceof WalkingEnemy) {
                //TODO Refactor, should the controller hold this logic or should world (World enforces how far we can hear).
                double newVolume = Math.sqrt((level.player.midX - character.getMidX()) * (level.player.midX - character.getMidX())) +
                        Math.sqrt((level.player.midY - character.getMidY()) * (level.player.midY - character.getMidY()));

                if (level.player.max_dist > newVolume) {
                    character.setSoundVolume(1f);
                } else {
                    float f = 1;
                    newVolume = newVolume - level.player.max_dist;
                    for (; newVolume > 0; newVolume -= 100) {
                        f -= 0.1;
                        if (f <= 0) {
                            f = 0;
                            break;
                        }
                    }
                    character.setSoundVolume(f);
                }
            }
        }
    }
}
