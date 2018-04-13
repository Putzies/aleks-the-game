package soldater.johannas.model;

import com.badlogic.gdx.utils.Timer;
import soldater.johannas.model.level.Block;
import soldater.johannas.model.level.Level;
import soldater.johannas.model.level.Parser;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;


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

    // Task object used for disabling a pickup effect after a set time.
    Timer.Task t;

    //Just testing
    BoundingBox bBox = generateConnected();

    public BoundingBox generateConnected(){
        Vector3 min = new Vector3(-400,0,0);
        Vector3 max = new Vector3(400,Block.WIDTH,0);

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

                if(character instanceof WalkingEnemy){
                    // Mid point caluclation for our main character.
                    double midX = character.x + character.getWidth() / 2;
                    double midY = character.y + character.getHeight() / 2;

                    // Check if the character object is our point of reference: Player.
                    if(character instanceof Player){

                        // Check every other character.
                        for(Character character1 : characters) {

                            // Mid point calcualtion for player.
                            double midX2 = character1.x + character1.getWidth() / 2;
                            double midY2 = character1.y + character1.getHeight() / 2;
                            double Max_dist = 800;
                            double newVolume;

                            // If we find the player object again, just continue.
                            if (character == character1) { continue; }

                            newVolume = Math.sqrt((midX - midX2) * (midX - midX2) + (midY - midY2) * (midY - midY2));

                            if (Max_dist > newVolume) {
                                character.soundVolume = 1f;
                                System.out.println(character.getSoundVolume());
                            } else {
                                 newVolume = Math.abs(newVolume - Max_dist);
                                float i = 1f;
                                for (; newVolume > 0; newVolume -= 100) {
                                    i -= 0.2f;
                                    if (i < 0) {
                                        i = 0;
                                        break;
                                     }
                                }

                                character1.x += 1;
                                character.setSoundVolume(i);
                                System.out.println(i + " " + character.getSoundVolume());

                            }

                        }
                    /*
                    // Check whether the character is outside of the player "hearing boundary".
                    if (character1.getX() < character.getX() - 300 || character1.getX() > character.getX() + 300 ){

                        // Arbitrary distance formula to mute the volume.
                        System.out.println((float)Math.abs((character1.getX() - character.getX()) -300 )* 0.001f);
                        character1.soundVolume = (float)Math.abs((character1.getX() - character.getX()) -300 )* 0.0001f;
                    } else {
                        // If within boundary then play on max volume.
                        character1.soundVolume = 1f;
                    }*/
                }
            }

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

    // Collision code for pickups, sets a flag which triggers the said effect on the proper entity.
    private void collidePickups(){
        for (Pickup d : this.getPickups()){

                boolean withinX = getPlayer().getX() + getPlayer().getWidth() > d.getX() &&
                        getPlayer().getX() < d.getX() + d.getWidth();

                boolean withinY = getPlayer().getY() + getPlayer().getHeight() > d.getY() &&
                        getPlayer().getY() + 1 < d.getY() + d.getHeight();

                // The collision detection is simple
                if (withinX && withinY){
                    if(d.getName().matches("wings")) {
                        // Trigger the Wings flag, put a timer task for 4 seconds
                        this.getPlayer1().setPickup(Player.WINGS, true);

                        // This is incredibly ugly but does the job for now.
                        t = new Timer.Task() {
                            @Override
                            public void run() {
                                getPlayer1().setPickup(Player.WINGS, false);
                            }
                        };
                        Timer.schedule(t,4);

                        //TODO Remove the pickup from the list

                    } else if(d.getName().matches("lunchbox")) {
                      //  System.out.println(d.getName());

                    } else if(d.getName().matches("baguette")){
                        // Trigger the Wings flag, put a timer task for 4 seconds
                        this.getPlayer1().setPickup(Player.BAGUETTE, true);

                        // This is incredibly ugly but does the job for now.
                        t = new Timer.Task() {
                            @Override
                            public void run() {
                                getPlayer1().setPickup(Player.BAGUETTE, false);
                            }
                        };
                        Timer.schedule(t,4);

                    } else if(d.getName().matches("energydrink")){
                        // Trigger the Wings flag, put a timer task for 4 seconds
                        this.getPlayer1().setPickup(Player.ENERGYDRINK, true);

                        // This is incredibly ugly but does the job for now.
                        t = new Timer.Task() {
                            @Override
                            public void run() {
                                getPlayer1().setPickup(Player.ENERGYDRINK, false);
                            }
                        };
                        Timer.schedule(t,4);


                    }
                }


        }
    }

    private void collideCharacters() {
        for(Character character : characters) {
            character.resetCollisions();

            /*
            if (character instanceof Player){ System.out.println(character.getX());}

            Vector3 min = new Vector3((float)character.getX(),(float)character.getY(),0);
            Vector3 max = new Vector3(character.getWidth(),character.getHeight(),0);

            BoundingBox bBox2 = new BoundingBox(min,max);

            if(bBox2.intersects(this.bBox)){
                boolean withinY = character.getY() + character.getHeight() > bBox.min.y &&
                        character.getY() + 1 < bBox.min.y + bBox.max.y;

                boolean withinX = character.getX() + character.getWidth() > bBox.min.x &&
                        character.getX() < bBox.min.x + bBox.max.x;

               // System.out.println("test2? " + withinY);

                if(withinY & withinX){
                    character.setCollision(Character.DOWN,true,bBox.min.y + bBox.max.y -1);
                }
            }*/

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
