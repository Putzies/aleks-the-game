package soldater.johannas;

import com.badlogic.gdx.ApplicationAdapter;
import soldater.johannas.control.PlayerController;
import soldater.johannas.model.World;
import soldater.johannas.view.Renderer;

public class Game extends ApplicationAdapter {
	private Renderer renderer;
	private World world;

	private PlayerController playerController;
	
	@Override
	public void create () {
		world = new World();
		renderer = new Renderer(world.getDrawables());
		playerController = new PlayerController(world.getPlayer());
		renderer.init();
	}

	@Override
	public void render () {
		playerController.update();
		world.update(1);
		renderer.render();
	}
	
	@Override
	public void dispose () {
		renderer.dispose();
	}
}
