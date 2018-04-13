package soldater.johannas.model.level;

import soldater.johannas.model.*;

import java.util.ArrayList;
import java.util.List;

public class Level {
    public final List<Block> blocks;
    public final Player player;
    public final List<WalkingEnemy> enemies;
    public final List<Score> scores;

    public List<Pickup> pickups;

    private final List<Lunchbox> lunchboxes;
    private final List<Wings> wings;
    private final List<EnergyDrink>energyDrinks;
    private final List<Baguette> baguettes;

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

    void addPickups() {
        pickups = new ArrayList<>();
        pickups.addAll(lunchboxes);
        pickups.addAll(energyDrinks);
        pickups.addAll(baguettes);
        pickups.addAll(wings);
    }

}
