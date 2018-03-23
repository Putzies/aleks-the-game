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
		renderer = new Renderer(world.getPlayer(), world.getDrawables());
		playerController = new PlayerController(world.getPlayer());
	}

	@Override
	public void render () {
		world.update(1);
		playerController.update();
		renderer.render();
	}
	
	@Override
	public void dispose () {
		renderer.dispose();
	}
}
