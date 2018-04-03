package soldater.johannas;

import com.badlogic.gdx.ApplicationAdapter;
import soldater.johannas.control.Controller;
import soldater.johannas.control.HangingEnemyController;
import soldater.johannas.control.WalkingEnemyController;
import soldater.johannas.control.PlayerController;
import soldater.johannas.model.HangingEnemy;
import soldater.johannas.model.WalkingEnemy;
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
		renderer = new Renderer(world.getPlayer(), world.getDrawables(), world.getHangingEnemies());
		controllers = new ArrayList<Controller>();
		controllers.add(new PlayerController(world.getPlayer()));
		for(WalkingEnemy e : world.getWalkingEnemies()) {
			controllers.add(new WalkingEnemyController(e));
		}
		for(HangingEnemy e : world.getHangingEnemies()) {
			controllers.add(new HangingEnemyController(e));
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
