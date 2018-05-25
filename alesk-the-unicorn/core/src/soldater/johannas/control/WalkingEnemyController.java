package soldater.johannas.control;

import soldater.johannas.model.Drawable;
import soldater.johannas.model.level.WalkingEnemy;

import java.util.Random;

/**
 * Controls the movement of walking enemies
 */
public class WalkingEnemyController implements Controller{

    private WalkingEnemy enemy;
    private Drawable player;
    private SoundController soundController;
    private boolean goingLeft = true;
    private Random random = new Random();

    public WalkingEnemyController(WalkingEnemy enemy, Drawable player, SoundController soundController) {
        this.enemy = enemy;
        this.player = player;
        this.soundController = soundController;
    }

    /**
     * Updates the enemy, and makes it go back and forth and jump in a random manner within a specified range
     */
    public void update() {
        if (random.nextInt(200) > 198 && enemy.isOnGround()) {
            enemy.jump();

            soundController.enemyJump(getDistanceToPlayer());
        }
        if ((enemy.getX() < enemy.getLeftBound() && goingLeft) || random.nextDouble() > 0.99 || enemy.collidesLeft()) {
            enemy.right();
            goingLeft = false;
        } else if ((enemy.getRightbound() < enemy.getX() && !goingLeft) || random.nextDouble() > 0.99 || enemy.collidesRight()){
            enemy.left();
            goingLeft = true;
        } else if(goingLeft) {
            enemy.left();
        } else if (!goingLeft) {
            enemy.right();
        }
    }

    /**
     * Calculates the distance between the player character and the enemy
     * @return the distance to the player
     */
    private double getDistanceToPlayer() {
        double dX = player.getX() - enemy.getX();
        double dY = player.getY() - enemy.getY();

        return Math.sqrt(dX * dX + dY * dY);
    }
}
