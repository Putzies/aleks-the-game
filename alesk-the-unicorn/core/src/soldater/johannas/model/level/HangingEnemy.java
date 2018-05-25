package soldater.johannas.model.level;

import soldater.johannas.model.Character;

/**
 * Models hanging enemies. Controlled by WalkingEnemyController
 */
public class HangingEnemy extends Character {
    public static final int WIDTH = 93;
    public static final int HEIGHT = 36;

    private double startY;

    private double range;

    public HangingEnemy(int x, int y) {
        super(x, y);

        this.startY = y;
        this.range = 200;
    }

    public HangingEnemy(int x, int y, int range) {
        super(x, y);

        this.startY = y;
        this.range = range;
    }

    public HangingEnemy() {
        super();
    }

    @Override
    public void update(double dTime) {
        if (!collisions[DOWN]) {
        }

        super.update(dTime);
    }

    public double getRange() {
        return range;
    }

    public double getStartY() {
        return startY;
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
        return "enemy";
    }

    public void down() {
        if (!collisions[super.DOWN]) {
            y -= 2;
        }
    }

    public void up() {
        if (!collisions[super.UP]) {
            y += 2;
        }
    }



}
