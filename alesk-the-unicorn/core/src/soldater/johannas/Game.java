package soldater.johannas;

import com.badlogic.gdx.ApplicationAdapter;
import soldater.johannas.control.Controller;
import soldater.johannas.control.HangingEnemyController;
import soldater.johannas.control.PlayerController;
import soldater.johannas.control.WalkingEnemyController;
import soldater.johannas.view.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Game extends ApplicationAdapter {
	private Renderer renderer;
	private soldater.johannas.model.Game game;

	private List<Controller> controllers;

	@Override
	public void create () {
		game = new soldater.johannas.model.Game();
		game.startGame("level1.json");
		renderer = new Renderer(game.getPlayer(), game.getDrawables(), game.getHangingEnemies());
		controllers = new ArrayList<>();
		controllers.add(new PlayerController(game.getPlayer()));
		game.getWalkingEnemies().forEach(e -> controllers.add(new WalkingEnemyController(e)));
		game.getHangingEnemies().forEach(e -> controllers.add(new HangingEnemyController(e)));
	}

	@Override
	public void render () {
		game.update(1);
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
