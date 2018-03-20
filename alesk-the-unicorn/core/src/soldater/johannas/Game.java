package soldater.johannas;

import com.badlogic.gdx.ApplicationAdapter;
import soldater.johannas.model.World;
import soldater.johannas.view.Renderer;

public class Game extends ApplicationAdapter {
	private Renderer renderer;
	private World world;
	
	@Override
	public void create () {
		world = new World();
		renderer = new Renderer(world.getDrawables());
		renderer.init();
	}

	@Override
	public void render () {
		world.update(1);
		renderer.render();
	}
	
	@Override
	public void dispose () {
		renderer.dispose();
	}
}
