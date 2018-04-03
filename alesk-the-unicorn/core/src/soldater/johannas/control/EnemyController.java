package soldater.johannas.control;

import soldater.johannas.model.Enemy;

import java.util.Random;

public class EnemyController {
    private Enemy enemy;
    private boolean goingLeft = true;
    private Random random = new Random();

    public EnemyController(Enemy enemy) {
        this.enemy = enemy;
    }

    public void update() {
        if (random.nextInt(200) > 198) {
            enemy.jump();
        }
        if ((enemy.getX() < enemy.getLeftBound() && goingLeft) || random.nextDouble() > 0.99) {
            enemy.right();
            goingLeft = false;
        } else if ((enemy.getRightbound() < enemy.getX() && !goingLeft) || random.nextDouble() > 0.99){
            enemy.left();
            goingLeft = true;
        } else if(goingLeft) {
            enemy.left();
        } else if (!goingLeft) {
            enemy.right();
        }
    }
}
