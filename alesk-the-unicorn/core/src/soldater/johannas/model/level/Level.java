package soldater.johannas.model.level;

import soldater.johannas.model.*;

import java.util.ArrayList;
import java.util.List;

public class Level {
    public final List<Platform> platforms;
    public final List<LavaPlatform> lavaPlatforms;
    public final List<SpikePlatform> spikePlatforms;
    public final Player player;
    public final List<WalkingEnemy> enemies;
    public final List<Score> scores;

    public List<Pickup> pickups;

    private final List<Lunchbox> lunchboxes;
    private final List<Wings> wings;
    private final List<EnergyDrink>energyDrinks;
    private final List<Baguette> baguettes;

    public int totalLunchboxes;
    public int takenLunchboxes;

    public Level(Player player,
                 List<Platform> platforms, List<LavaPlatform> lavaPlatforms, List<SpikePlatform> spikePlatforms,
                 List<WalkingEnemy> enemies,
                 List<Lunchbox> lunchboxes, List<Score> scores, List<Wings> wings,
                 List<Baguette> baguettes, List<EnergyDrink>energyDrinks) {
        this.player = player;
        this.platforms = platforms;
        this.lavaPlatforms = lavaPlatforms;
        this.spikePlatforms = spikePlatforms;
        this.enemies = enemies;
        this.lunchboxes = lunchboxes;
        this.scores = scores;
        this.wings = wings;
        this.energyDrinks = energyDrinks;
        this.baguettes = baguettes;
        totalLunchboxes = lunchboxes.size();
        takenLunchboxes = 0;
    }

    public void construct() {
        addPickups();
        platforms.forEach(Platform::construct);
        spikePlatforms.forEach(SpikePlatform::construct);
        lavaPlatforms.forEach(LavaPlatform::construct);
    }

    private void addPickups() {
        pickups = new ArrayList<>();
        pickups.addAll(lunchboxes);
        totalLunchboxes = lunchboxes.size();
        pickups.addAll(energyDrinks);
        pickups.addAll(baguettes);
        pickups.addAll(wings);
    }

    public void incrementLunchBoxes() {
        takenLunchboxes++;
    }

}
