package soldater.johannas.model.level;

import soldater.johannas.model.level.platforms.GrassPlatform;
import soldater.johannas.model.level.platforms.LavaPlatform;
import soldater.johannas.model.level.platforms.Platform;
import soldater.johannas.model.level.platforms.SpikePlatform;
import soldater.johannas.model.level.pickups.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a complete level in the game
 */
public class Level {
    public List<Platform> platforms;
    private final List<GrassPlatform> grassPlatforms;
    private final List<LavaPlatform> lavaPlatforms;
    private final List<SpikePlatform> spikePlatforms;
    public final Player player;
    public final List<WalkingEnemy> enemies;

    public List<Pickup> pickups;

    private final List<Lunchbox> lunchboxes;
    private final List<Wings> wings;
    private final List<EnergyDrink>energyDrinks;
    private final List<Dumbbell> dumbbells;

    public int totalLunchboxes;
    public int takenLunchboxes;

    public Level(Player player,
                 List<GrassPlatform> grassPlatforms, List<LavaPlatform> lavaPlatforms, List<SpikePlatform> spikePlatforms,
                 List<WalkingEnemy> enemies,
                 List<Lunchbox> lunchboxes, List<Wings> wings,
                 List<Dumbbell> dumbbells, List<EnergyDrink>energyDrinks) {
        this.player = player;
        this.grassPlatforms = grassPlatforms;
        this.lavaPlatforms = lavaPlatforms;
        this.spikePlatforms = spikePlatforms;
        this.platforms = new ArrayList<>();
        platforms.addAll(grassPlatforms);
        platforms.addAll(spikePlatforms);
        platforms.addAll(lavaPlatforms);
        this.enemies = enemies;
        this.lunchboxes = lunchboxes;
        this.wings = wings;
        this.energyDrinks = energyDrinks;
        this.dumbbells = dumbbells;
        totalLunchboxes = lunchboxes.size();
        takenLunchboxes = 0;
    }

    public void construct() {
        addPickups();
        platforms = new ArrayList<>();
        grassPlatforms.forEach(Platform::construct);
        spikePlatforms.forEach(SpikePlatform::construct);
        lavaPlatforms.forEach(LavaPlatform::construct);
        platforms.addAll(lavaPlatforms);
        platforms.addAll(spikePlatforms);
        platforms.addAll(grassPlatforms);
    }

    private void addPickups() {
        pickups = new ArrayList<>();
        pickups.addAll(lunchboxes);
        totalLunchboxes = lunchboxes.size();
        pickups.addAll(energyDrinks);
        pickups.addAll(dumbbells);
        pickups.addAll(wings);
    }

}
