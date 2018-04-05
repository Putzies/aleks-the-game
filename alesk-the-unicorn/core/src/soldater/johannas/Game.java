package soldater.johannas;

import com.badlogic.gdx.ApplicationAdapter;
import soldater.johannas.control.Controller;
import soldater.johannas.control.PlayerController;
import soldater.johannas.model.World;
import soldater.johannas.view.Renderer;

import java.util.List;

public class Game extends ApplicationAdapter {
	private Renderer renderer;
	private World world;

	private List<Controller> controllers;

	@Override
	public void create () {
		world = new World();
		world.startGame("level1.json");
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
		for (Controller c : controllers) {
			c.update();
		}
		renderer.render();
	}
	
	@Override
	public void dispose () {
		renderer.dispose();
	}
}
