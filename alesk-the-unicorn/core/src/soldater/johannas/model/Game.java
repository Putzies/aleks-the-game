package soldater.johannas.model;

import java.util.Timer;

import soldater.johannas.model.level.*;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

public class Game implements Entity, DrawableGame {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private Level level;

    private util.Timer timer = new util.Timer();

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
        collideTerrain(dTime);
        collideCharacters(dTime);
        collidePickups();
    }

    @Override
    public util.Timer getTimer() {
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

    // Collision code for pickups, sets a flag which triggers the said effect on the proper entity.
    private void collidePickups() {
        for (Iterator<Pickup> iterator = level.pickups.iterator(); iterator.hasNext();) {
            Pickup pickup = iterator.next();

            boolean withinX = isWithinX(getPlayer(),pickup);
            boolean withinY = isWithinY(getPlayer(),pickup);

            boolean remove = true;

            // Detecting a collision is simple
            if (withinX && withinY) {
                if (pickup.getName().matches("wings")) {

                    // Trigger the Wings flag, put a taskTimer task for 4 seconds
                    level.player.setPickup(Player.WINGS, true);

                    // Schedule the disabling of the powerup sometime soon.
                    taskTimer.schedule(generateTask(Player.WINGS, false), 4000);

                } else if (pickup.getName().matches("lunchbox")) {
                    level.incrementLunchBoxes();
                    // TODO Define behavior for picking up a luncbhox.

                } else if (pickup.getName().matches("baguette")) {

                    // Trigger the Baguette flag, put a taskTimer task for 4 seconds
                    level.player.setPickup(Player.BAGUETTE, true);

                    // Schedule the disabling of the powerup sometime soon.
                    taskTimer.schedule(generateTask(Player.BAGUETTE, false), 4000);

                } else if (pickup.getName().matches("energydrink")) {

                    // Trigger the Energydrink flag, put a taskTimer task for 4 seconds
                    level.player.setPickup(Player.ENERGYDRINK, true);

                    // Schedule the disabling of the powerup sometime soon.
                    taskTimer.schedule(generateTask(Player.ENERGYDRINK, false), 4000);
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
    private void collideCharacters(double dTime) {
        // We will constantly be using player. So lets store it for the time being.
        Player player = level.player;

        for (Character character : characters) {
            if (character == player) { continue; }
            boolean withinX = isWithinX(player, character);
            boolean withinY = isWithinY(player, character);

            // Check if player and some character are colliding.
            if (withinX && withinY) {
                collideHarmful(player, dTime);
            }
        }

    }

    private void collideTerrain(double dTime){
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

        }
    }

    // Consequences of colliding with harmful objects
    private void collideHarmful(Player player, double dTime) {
        // Either we are facing Right or we are facing Left
        if (player.getDirection() == Drawable.RIGHT) {
            player.yVel = 300;
            player.xVel = -300;

        } else {
            player.yVel = 300;
            player.xVel = 300;
        }

        if(!player.isDamaged()) {
            player.decrementLife();
        }

        player.knockbacked = true;
        player.damaged = true;

        // Set timer to not take damage again immediately
        t = new TimerTask(){
            @Override
            public void run() {
                player.damaged = false;
            }
        };
        taskTimer.schedule(t, (int)(dTime*10000));
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
