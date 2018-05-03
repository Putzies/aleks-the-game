package soldater.johannas.control;

import soldater.johannas.model.level.HangingEnemy;

import java.util.Random;

public class HangingEnemyController implements Controller{
    private HangingEnemy enemy;
    private Random random = new Random();
    private boolean goingUp = false;

    public HangingEnemyController(HangingEnemy hangingEnemy) {
        this.enemy = hangingEnemy;
    }

    public void update() {
        if ((enemy.getY() > enemy.getStartY() && goingUp) || random.nextDouble() > 0.99) {
            enemy.down();
            goingUp = false;
        } else if ((enemy.getStartY() - enemy.getRange() > enemy.getY() && !goingUp) || random.nextDouble() > 0.99 || enemy.isOnGround()) {
            enemy.up();
            goingUp = true;
        } else if (goingUp) {
            enemy.up();
        } else {
            enemy.down();
        }

    }
}
