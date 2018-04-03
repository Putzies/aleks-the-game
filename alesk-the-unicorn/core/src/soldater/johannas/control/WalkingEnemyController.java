package soldater.johannas.control;

import soldater.johannas.model.WalkingEnemy;

import java.util.Random;

public class WalkingEnemyController implements Controller{
    private WalkingEnemy enemy;
    private boolean goingLeft = true;
    private Random random = new Random();

    public WalkingEnemyController(WalkingEnemy enemy) {
        this.enemy = enemy;
    }

    public void update() {
        if (random.nextInt(200) > 198 && enemy.isOnGround()) {
            enemy.jump();
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
}
