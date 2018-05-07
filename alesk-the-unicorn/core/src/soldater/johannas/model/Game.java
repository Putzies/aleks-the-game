package soldater.johannas.model;

import java.util.Timer;

import soldater.johannas.model.level.*;
import soldater.johannas.model.level.blocks.Platform;
import soldater.johannas.model.level.pickups.LevelPickup;
import soldater.johannas.model.level.pickups.Lunchbox;
import soldater.johannas.model.level.pickups.Pickup;
import soldater.johannas.model.level.pickups.PlayerPickup;


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
        // Future code cleanup.
        AABB playerBox = new AABB(getPlayer().getX(), getPlayer().getY(), getPlayer().getWidth(), getPlayer().getHeight());
        AABB other;

        for (Iterator<Pickup> iterator = level.pickups.iterator(); iterator.hasNext();) {
            Pickup pickup = iterator.next();

            other = new AABB(pickup.getX(), pickup.getY(), pickup.getWidth(), pickup.getHeight());

            //boolean withinX = isWithinX(getPlayer(),pickup);
            //boolean withinY = isWithinY(getPlayer(),pickup);

            boolean remove = true;

            // This is the best solution i could come up with for now.
            // Still uses pickup at its core so we loop over that list, but we use instance of to do something specific.
            if (playerBox.intersects(other)) {
                if (pickup instanceof LevelPickup){
                    ((LevelPickup) pickup).doIt(level);
                }
                if (pickup instanceof PlayerPickup){
                    ((PlayerPickup) pickup).doIt(getPlayer());
                }

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

        AABB playerBox = new AABB(getPlayer().getX(), getPlayer().getY(), getPlayer().getWidth(), getPlayer().getHeight());
        AABB other;

        for (Character character : characters) {
            if (character == player) { continue; }
            other = new AABB(character.getX(), character.getY(), character.getWidth(), character.getHeight());;

            //boolean withinX = isWithinX(player, character);
            //boolean withinY = isWithinY(player, character);

            // Check if player and some character are colliding.
            if (playerBox.intersects(other)) {
                player.damage();
            }
        }
        for (Character character : hangingEnemies){
            // hanging enemies doesnt hold player so unnecessary check.
            //if (character == player) { continue; }

            //boolean withinX = isWithinX(player, character);
            //boolean withinY = isWithinY(player, character);

            other = new AABB(character.getX(), character.getY(), character.getWidth(), character.getHeight());;
            // Check if player and some character are colliding.
            if (playerBox.intersects(other)) {
                player.damageInverted();
            }
        }

    }

    private void collideTerrain(){
            AABB playerBox; // Not really the "playerbox" here but lets use it.
            AABB other;     // Preferably platformBox but lets use this for now. (Easier to reason about improvements with same names everywhere).

            // Just loop through everything now. Creating a new other for each item to collide against and check for intersect.
            for (Character character : characters) {
                character.resetCollisions();

                playerBox = new AABB(character.getX(), character.getY(), character.getWidth(), character.getHeight());

                for (Platform platform: level.platforms) {
                    other = new AABB(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());

                    if (playerBox.intersects(other)) {
                        if (playerBox.getX() + playerBox.getWidth() > other.getX() + other.getWidth()
                                && playerBox.getX() < other.getX() + other.getWidth()
                                && character.xVel < 0) {

                                /* This is probably the greatest hack i've done to date.
                                 * We mix collision detection types for the left/right cases into "look-ahead" type
                                 * So instead of just checking left right we also check whether what we collide into is
                                 * on the same level as us, which means that we should be able to freely run over
                                 */
                                if(Math.abs(playerBox.getY() - (other.getY()+ other.getHeight()-1)) < 1 && Math.abs(playerBox.getY()- (other.getY()+ other.getHeight()-1)) > 0){
                                    character.setCollision(Character.DOWN, other.getY() + other.getHeight() - 1);
                                } else {
                                    character.setCollision(Character.LEFT, other.getX() + other.getWidth() + 1);
                                }

                        } else if (playerBox.getX() < other.getX() &&
                                playerBox.getX() + playerBox.getWidth() > other.getX() &&
                                character.xVel > 0) {
                            if (Math.abs(playerBox.getY() - (other.getY() + other.getHeight() - 1)) < 1 && Math.abs(playerBox.getY() - (other.getY() + other.getHeight() - 1)) > 0) {
                                character.setCollision(Character.DOWN, other.getY() + other.getHeight() - 1);
                            } else {
                                character.setCollision(Character.RIGHT, other.getX() - playerBox.getWidth() - 1);
                            }
                        }


                        else if (playerBox.getY() + playerBox.getHeight() > other.getY() + other.getHeight() &&
                                playerBox.getY() < other.getY() + other.getHeight() && character.yVel < 0) {

                                if (platform.isHarmful()) {
                                    character.damage();
                                } else {
                                    character.setCollision(Character.DOWN, other.getY() + other.getHeight() - 1);
                                }

                        } else if (playerBox.y < other.y && playerBox.y + playerBox.HEIGHT > other.y && character.yVel > 0) {
                            character.setCollision(Character.UP,  other.y - playerBox.getHeight() - 1);

                        }
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
    */

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