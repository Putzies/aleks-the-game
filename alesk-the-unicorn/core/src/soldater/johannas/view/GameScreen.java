package soldater.johannas.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import soldater.johannas.AleskTheUnicorn;
import soldater.johannas.control.EnemyController;
import soldater.johannas.control.PlayerController;
import soldater.johannas.model.Enemy;
import soldater.johannas.model.World;
import soldater.johannas.view.Renderer;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

	private final Game game;
	private Renderer renderer;
	private World world;

	private PlayerController playerController;
	private List<EnemyController> enemyControllers;

	public GameScreen(final AleskTheUnicorn game, String level) {
		this.game = game;

		System.out.println(level);
		// Initialize the world!
		world = new World();
		world.startGame(level);
		renderer = new Renderer(world.getPlayer(), world.getDrawables());
		playerController = new PlayerController(world.getPlayer());
		enemyControllers = new ArrayList<EnemyController>();
		for (Enemy e : world.getEnemies()) {
			enemyControllers.add(new EnemyController(e));
		}
	}


	@Override
	public void show() {

	}

	@Override
	public void render (float delta) {
		world.update(1);
		playerController.update();
		for (EnemyController ec : enemyControllers) {
			ec.update();
		}
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		renderer.dispose();
	}
}
