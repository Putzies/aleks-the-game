package soldater.johannas.model;

import java.util.Timer;

import soldater.johannas.model.level.*;
import soldater.johannas.model.level.blocks.Platform;
import soldater.johannas.model.level.pickups.Lunchbox;
import soldater.johannas.model.level.pickups.Pickup;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class Game implements Entity, DrawableGame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private Level level;

    private soldater.johannas.util.Timer timer = new soldater.johannas.util.Timer();

    private List<Entity> entities;
    private List<Character> characters;
    private List<HangingEnemy> hangingEnemies;


    // Task object used for disabling a pickup effect after a set time.
    private TimerTask t;
    private Timer taskTimer = new Timer();

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

        level.platforms
                .stream()
                .filter(p -> p.getHangingEnemies() != null && p.getHangingEnemies().size() > 0)
                .forEach(p -> hangingEnemies.addAll(p.getHangingEnemies())
            );

        return level != null;
    }

    @Override
    public List<Drawable> getDrawables() {
        List<Drawable> allObjects = new ArrayList<>();
        level.platforms.forEach(p -> allObjects.addAll(p.getBlocks()));
        allObjects.addAll(level.enemies);
        allObjects.addAll(hangingEnemies);
        allObjects.addAll(level.pickups);
        return allObjects;
    }

    @Override
    public int getTakenLunchBoxes(){
        return level.takenLunchboxes;
    };

    @Override
    public int getTotalLunchBoxes() {
        return level.totalLunchboxes;
    }


    @Override
    public Player getPlayer() {
        return level.player;
    }

    @Override
    public void update(double dTime) {
        timer.update((float)dTime);

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
    public soldater.johannas.util.Timer getTimer() {
        return timer;
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

    // Version 2: Checks for a collision and triggers the response.
    private void collidePickups() {
        for (Iterator<Pickup> iterator = level.pickups.iterator(); iterator.hasNext();) {
            Pickup pickup = iterator.next();

            boolean withinX = isWithinX(getPlayer(),pickup);
            boolean withinY = isWithinY(getPlayer(),pickup);

            boolean remove = true;

            // Detecting a collision is simple
            if (withinX && withinY) {
                if (pickup instanceof Lunchbox){
                    level.takenLunchboxes += 1;
                }

                pickup.doIt(getPlayer());

            } else {
                remove = false;
            }

            if (remove) {
                iterator.remove();
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
                player.damage();
            }
        }
        for (Character character : hangingEnemies){
            if (character == player) { continue; }
            boolean withinX = isWithinX(player, character);
            boolean withinY = isWithinY(player, character);

            // Check if player and some character are colliding.
            if (withinX && withinY) {
                player.damageInverted();
            }
        }

    }

    private void collideTerrain(){
            AABB playerBox;
            AABB other;

            // Just loop through everything now. Creating a new other for each item to collide against and check for intersect.
            for (Character character : characters) {
                character.resetCollisions();

                playerBox = new AABB(character.getX(), character.getY(), character.getWidth(), character.getHeight());

                for (Platform platform: level.platforms) {
                    other = new AABB(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());

                    if (playerBox.intersects(other)) {

                        // Check Right side
                        if (playerBox.getX() < other.getX() && playerBox.getX() + playerBox.getWidth() > other.getX()) {
                            character.setCollision(Character.RIGHT,  other.getX() - playerBox.getWidth());

                        // Check Left side
                        } else if (playerBox.getX() + playerBox.getWidth() > other.getX() + other.getWidth() &&
                                playerBox.getX() < other.getX() + other.getWidth()) {
                            character.setCollision(Character.LEFT,  other.getX());
                        }

                        // Check Down, and check if the platform is harmful.
                         else if (playerBox.getY() + playerBox.getHeight() > other.getY() + other.getHeight() &&
                                playerBox.getY() < other.getY() + other.getHeight()) {
                            if (platform.isHarmful()) {
                                character.damage();
                            } else {
                                character.setCollision(Character.DOWN,  other.getY() + other.getHeight() - 2);
                            }

                         // Check Up
                        } else if (playerBox.y < other.y && playerBox.y + playerBox.HEIGHT > other.y) {
                            character.setCollision(Character.UP,  other.y - playerBox.getHeight());


                        // Bottom case that should never be reached in an Axis-Aligned setting.
                        } else {
                            System.out.println("Intersect but against what?");
                        }
                    }
                }
            }

        /*
        for (Character character : characters) {
            character.resetCollisions();

            for (Platform platform : level.platforms) {
                // Checks if any point at all is intersecting, if not then we can ignore the rest of the statements
                boolean withinX = isWithinX(character,platform);
                boolean withinY = isWithinY(character,platform);

                // DOWN
                if (withinX &&
                        character.getY() + character.getHeight() > platform.getY() + platform.getHeight() &&
                        character.getY() < platform.getY() + platform.getHeight()) {
                    if(platform.isHarmful()) {
                        collideHarmful(level.player, dTime);
                    } else {
                        character.setCollision(Character.DOWN, true, platform.getY() + platform.getHeight() - 1);
                    }
                }

                // TOP
                if (withinX &&
                        character.getY() < platform.getY() &&
                        character.getY() + character.getHeight() > platform.getY()) {
                    character.setCollision(Character.UP, true, platform.getY() - character.getHeight());
                }

                // RIGHT
                if (withinY &&
                        character.getX() < platform.getX() &&
                        character.getX() + character.getWidth() > platform.getX()) {
                    character.setCollision(Character.RIGHT, true, platform.getX() - character.getWidth());
                }

                // LEFT
                if (withinY &&
                        character.getX() + character.getWidth() > platform.getX() + platform.getWidth() &&
                        character.getX() < platform.getX() + platform.getWidth()) {
                    character.setCollision(Character.LEFT, true, platform.getX());
                }
            }

        }*/
    }

    public List<WalkingEnemy> getWalkingEnemies() {
        return level.enemies;
    }

    @Override
    public List<HangingEnemy> getHangingEnemies() {
        return hangingEnemies;
    }

    // Generate the appropriate Task given an n and the truth value.
    private TimerTask generateTask(int n, boolean val) {
        TimerTask task = new TimerTask() {
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

    //TODO: Make less ugly <3
    private boolean isWithinX(Drawable affector, Platform affected) {
        boolean withinX = affector.getX() + affector.getWidth() > affected.getX() &&
                affector.getX() < affected.getX() + affected.getWidth();

        return withinX;
    }

    private boolean isWithinY(Drawable affector, Platform affected) {
        boolean withinY = affector.getY() + affector.getHeight() > affected.getY() &&
                affector.getY() + 1 < affected.getY() + affected.getHeight();

        return withinY;
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
