package soldater.johannas;

import com.badlogic.gdx.ApplicationAdapter;
import soldater.johannas.control.Controller;
import soldater.johannas.control.HangingEnemyController;
import soldater.johannas.control.PlayerController;
import soldater.johannas.control.WalkingEnemyController;
import soldater.johannas.model.World;
import soldater.johannas.view.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Game extends ApplicationAdapter {
	private Renderer renderer;
	private World world;

	private List<Controller> controllers;

	@Override
	public void create () {
		world = new World();
		world.startGame("level1.json");
		renderer = new Renderer(world.getPlayer(), world.getDrawables(), world.getHangingEnemies());
		controllers = new ArrayList<>();
		controllers.add(new PlayerController(world.getPlayer()));
		world.getWalkingEnemies().forEach(e -> controllers.add(new WalkingEnemyController(e)));
		world.getHangingEnemies().forEach(e -> controllers.add(new HangingEnemyController(e)));
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
