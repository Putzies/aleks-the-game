package soldater.johannas.model;

import soldater.johannas.model.level.*;
import soldater.johannas.model.level.blocks.Platform;
import soldater.johannas.model.level.pickups.LevelPickup;
import soldater.johannas.model.level.pickups.Pickup;
import soldater.johannas.model.level.pickups.PlayerPickup;
import soldater.johannas.util.Parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game implements DrawableGame {
    private Level level;

    private soldater.johannas.util.Timer timer = new soldater.johannas.util.Timer();

    private List<Entity> entities;
    private List<Character> characters;
    private List<HangingEnemy> hangingEnemies;

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

    public void update(double dTime) {
        timer.update((float)dTime);

        for (Entity entity : entities) {
            entity.update(dTime);
        }

        level.player.update(dTime);

        // Ordering here is important. Characters before Terrain will cause bugs.
        collideTerrain();
        collideCharacters();
        collidePickups();
    }

    @Override
    public soldater.johannas.util.Timer getTimer() {
        return timer;
    }

    // Version 2: Checks for a collision and triggers the response.
    private void collidePickups() {
        // Future code cleanup.
        AABB playerBox = new AABB(getPlayer().getX(), getPlayer().getY(), getPlayer().getWidth(), getPlayer().getHeight());
        AABB other;

        for (Iterator<Pickup> iterator = level.pickups.iterator(); iterator.hasNext();) {
            Pickup pickup = iterator.next();

            other = new AABB(pickup.getX(), pickup.getY(), pickup.getWidth(), pickup.getHeight());

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

            // Check if player and some character are colliding.
            if (playerBox.intersects(other)) {

                // If strong, crush the spiders
                if (player.getPickupState() == Player.STRONG) {
                    character.damage();
                } else { // Else take some damage
                    player.damage();
                }
            }
        }
        for (Character character : hangingEnemies){
            other = new AABB(character.getX(), character.getY(), character.getWidth(), character.getHeight());;
            // Check if player and some character are colliding.
            if (playerBox.intersects(other) && player.getPickupState() != Player.STRONG) {
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

                playerBox = new AABB(character.getX(), character.getY() + 1, character.getWidth(), character.getHeight());

                for (Platform platform: level.platforms) {
                    other = new AABB(platform.getX(), platform.getY(), platform.getWidth(), platform.getHeight());

                    if (playerBox.intersects(other)) {
                        if (playerBox.getX() + playerBox.getWidth() > other.getX() + other.getWidth() && character.xVel < 0) {
                            if(playerBox.lookaheadY(other)){
                                character.setCollision(Character.DOWN, other.getY() + other.getHeight() - 1);
                            } else {
                                character.setCollision(Character.LEFT, other.getX() + other.getWidth() + 1);
                            }

                        } else if (playerBox.getX() < other.getX() && character.xVel > 0) {
                            if (playerBox.lookaheadY(other)) {
                                character.setCollision(Character.DOWN, other.getY() + other.getHeight() - 1);
                            } else {
                                character.setCollision(Character.RIGHT, other.getX() - playerBox.getWidth() - 1);
                            }
                        }

                        else if (playerBox.getY() + playerBox.getHeight() > other.getY() + other.getHeight() && character.yVel < 0) {
                                if (platform.isHarmful()) {
                                    character.damage();
                                } else {
                                    character.setCollision(Character.DOWN, other.getY() + other.getHeight() - 1);
                                }

                        } else if (playerBox.y < other.y && character.yVel > 0) {
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
}