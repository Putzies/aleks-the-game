package soldater.johannas;

import com.badlogic.gdx.ApplicationAdapter;
import soldater.johannas.control.EnemyController;
import soldater.johannas.control.PlayerController;
import soldater.johannas.model.Enemy;
import soldater.johannas.model.World;
import soldater.johannas.view.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Game extends ApplicationAdapter {
	private Renderer renderer;
	private World world;

	private PlayerController playerController;
	private List<EnemyController> enemyControllers;
	
	@Override
	public void create () {
		world = new World();
		renderer = new Renderer(world.getPlayer(), world.getDrawables());
		playerController = new PlayerController(world.getPlayer());
		enemyControllers = new ArrayList<EnemyController>();
		for (Enemy e : world.getEnemies()) {
			enemyControllers.add(new EnemyController(e));
		}

	}

	@Override
	public void render () {
		world.update(1);
		playerController.update();
		for (EnemyController ec : enemyControllers) {
			ec.update();
		}
		renderer.render();
	}
	
	@Override
	public void dispose () {
		renderer.dispose();
	}
}
