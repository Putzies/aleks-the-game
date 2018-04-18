package soldater.johannas.model;

import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.utils.Box2DBuild;
import com.badlogic.gdx.utils.Timer;
import soldater.johannas.model.level.Block;
import soldater.johannas.model.level.Level;
import soldater.johannas.model.level.Parser;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import soldater.johannas.model.level.Platform;


import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class Game implements Entity {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;

    private Level level;

    private List<Entity> entities;
    private List<Drawable> drawables;
    private List<Character> characters;
    private List<HangingEnemy> hangingEnemies;
    private Player player;
    private Platform platform;


    // Task object used for disabling a pickup effect after a set time.
    Timer.Task t;

    //Just testing
    public BoundingBox generateConnected(int n){
        Vector3 min;
        Vector3 max;
        if(n == 0) {
            min = new Vector3((float) platform.getX(), (float) platform.getY(), 0);
            max = new Vector3((float) platform.getX() + platform.getWidth(), (float) platform.getY() + platform.getHeight(), 0);

        }
        else {
            min = new Vector3((float) getPlayer1().getX(), (float) getPlayer1().getY(), 0);
            max = new Vector3((float) getPlayer1().getX() + getPlayer1().getWidth(), (float) getPlayer1().getY() + getPlayer1().getHeight(), 0);

        }
        BoundingBox bBox2 = new BoundingBox(min,max);

        return bBox2;
    }

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

        // Testing platform
        List<Block> quickList = new ArrayList<>();

        for(Block b: level.blocks){
            if ( b.getX() >= 0 && b.getY() + b.getHeight() <= 50){
                quickList.add(b);

            }
        }
        platform = new Platform(0,0,quickList,1,1*quickList.size());

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

    // Needed for manipulating the state
    public Player getPlayer1(){return level.player;}

    @Override
    public void update(double dTime) {
        for (Character character : characters) {
            character.update(dTime);

            if (character instanceof WalkingEnemy){
                //TODO Refactor, should the controller hold this logic or should world (World enforces how far we can hear).
                double newVolume = Math.sqrt((level.player.midX- character.getMidX()) * (level.player.midX- character.getMidX())) +
                                   Math.sqrt((level.player.midY - character.getMidY()) * (level.player.midY - character.getMidY()));

                if (level.player.max_dist > newVolume){ character.setSoundVolume(1f); }
                else {
                    float f = 1;
                    newVolume = newVolume - level.player.max_dist;
                    for (; newVolume > 0; newVolume -=100){
                        f -= 0.1;
                        if( f <= 0){ f = 0; break;}
                    }
                    character.setSoundVolume(f);
                }
            }
        }
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
    private void collidePickups(){
        for (Pickup d : this.getPickups()){
                // TODO Refactor, make withinX/WithinY own methods.
                boolean withinX = getPlayer().getX() + getPlayer().getWidth() > d.getX() &&
                        getPlayer().getX() < d.getX() + d.getWidth();

                boolean withinY = getPlayer().getY() + getPlayer().getHeight() > d.getY() &&
                        getPlayer().getY() + 1 < d.getY() + d.getHeight();

                // Detecting a collision is simple
                // TODO Remove the pickup from the list
                if (withinX && withinY){
                    if(d.getName().matches("wings")) {

                        // Trigger the Wings flag, put a timer task for 4 seconds
                        this.getPlayer1().setPickup(Player.WINGS, true);

                        // Schedule the disabling of the powerup sometime soon.
                        Timer.schedule(generateTask(Player.WINGS, false),4);

                    } else if(d.getName().matches("lunchbox")) {
                        // TODO Define behavior for picking up a luncbhox.

                    } else if(d.getName().matches("baguette")){

                        // Trigger the Baguette flag, put a timer task for 4 seconds
                        this.getPlayer1().setPickup(Player.BAGUETTE, true);

                        // Schedule the disabling of the powerup sometime soon.
                        Timer.schedule(generateTask(Player.BAGUETTE, false),4);

                    } else if(d.getName().matches("energydrink")){

                        // Trigger the Energydrink flag, put a timer task for 4 seconds
                        this.getPlayer1().setPickup(Player.ENERGYDRINK, true);

                        // Schedule the disabling of the powerup sometime soon.
                        Timer.schedule(generateTask(Player.ENERGYDRINK, false),4);
                    }
                }
        }
    }

    // Collision code for player versus some character.
    private void collideCharacters(){
        //More efficient version - try 1.

        // We will constantly be using player. So lets store him for the time being.
        Player player = getPlayer1();

        // Cause: We loop over characters twice, first to find the player and then to check against others.
        // Fix: Comment out the outer loop - replace every instance of character with player.
        //for (Character character : characters) {
            if (player instanceof Player) {
                for (Character character1 : characters) {
                    if (character1 == player) { continue; }

                    boolean withinX = player.getX() + player.getWidth() > character1.getX() &&
                            player.getX() < character1.getX() + character1.getWidth();

                    boolean withinY = player.getY() + player.getHeight() > character1.getY() &&
                            player.getY() + 1 < character1.getY() + character1.getHeight();

                    // Check if player and some character are colliding.
                    if (withinX && withinY) {
                        player.knockbacked = true;

                        // Either we are facing Right or we are facing Left
                        if (player.getDirection() == Drawable.RIGHT) {
                            player.yVel = 10;
                            player.xVel = -10;

                        } else {
                            player.yVel = 10;
                            player.xVel = 10;
                        }
                    }
                }
          //  }
        }

        //Ineffective version.
        for (Character character : characters) {
            if (character instanceof Player) {
                for (Character character1 : characters) {
                    if (character1 == character) { continue; }

                    boolean withinX = character.getX() + character.getWidth() > character1.getX() &&
                            character.getX() < character1.getX() + character1.getWidth();

                    boolean withinY = character.getY() + character.getHeight() > character1.getY() &&
                            character.getY() + 1 < character1.getY() + character1.getHeight();

                    // Check if player and some character are colliding.
                    if (withinX && withinY) {
                        character.knockbacked = true;

                        // Either we are facing Right or we are facing Left
                        if (character.getDirection() == Drawable.RIGHT) {
                            character.yVel = 10;
                            character.xVel = -10;

                        } else {
                            character.yVel = 10;
                            character.xVel = 10;
                        }
                    }
                }
            }
        }

    }
    private void collideTerrain() {
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
        for(Character character : characters) {
            character.resetCollisions();

            for (Block block : level.blocks) {
                // Checks if any point at all is intersecting, if not then we can ignore the rest of the statements
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

    // Generate the appropriate Task given an n and the truth value.
    private Timer.Task generateTask(int n, boolean val){
        Timer.Task task = new Timer.Task() {
            @Override
            public void run() {
                getPlayer1().setPickup(n, val);
            }
        };

        return task;
    }
}
