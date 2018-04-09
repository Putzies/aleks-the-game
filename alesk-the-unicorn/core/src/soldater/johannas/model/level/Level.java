package soldater.johannas.model.level;

import soldater.johannas.model.*;

import java.util.List;

public class Level {
    public final List<Block> blocks;
    public final Player player;
    public final List<WalkingEnemy> enemies;
    public final List<Pickup> pickups;
    public final List<Score> scores;

    public Level(Player player, List<Block> blocks, List<WalkingEnemy> enemies, List<Pickup>pickups,List<Score> scores){
        this.player = player;
        this.blocks = blocks;
        this.enemies = enemies;
        this.pickups = pickups;
        this.scores = scores;
    }
    /*
      public final List<Lunchbox> lunchboxes;
      public final List<Wings> wings;
      public final List<EnergyDrink>energyDrinks;
      public final List<Baguette> baguettes;

    public Level(Player player, List<Block> blocks, List<WalkingEnemy> enemies,
                                List<Lunchbox> lunchboxes, List<Score> scores,List<Wings> wings,
                                List<Baguette> baguettes,List<EnergyDrink>energyDrinks) {
        this.player = player;
        this.blocks = blocks;
        this.enemies = enemies;
        this.lunchboxes = lunchboxes;
        this.scores = scores;
        this.wings = wings;
        this.energyDrinks = energyDrinks;
        this.baguettes = baguettes;
    }
    */

}
