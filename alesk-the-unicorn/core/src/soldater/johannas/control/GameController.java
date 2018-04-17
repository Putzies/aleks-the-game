package soldater.johannas.control;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import soldater.johannas.AleskTheUnicorn;
import soldater.johannas.view.Renderer;

import java.util.ArrayList;
import java.util.List;

public class GameController implements Screen {

	private final Game alesk;
	private Renderer renderer;
	private soldater.johannas.model.Game game;

	private List<Controller> controllers;

	public GameController(final AleskTheUnicorn alesk, String level) {
		this.alesk = alesk;

		System.out.println(level);
		// Initialize the world!
		game = new soldater.johannas.model.Game();
		game.startGame(level);

		renderer = new Renderer(game.getPlayer(), game.getDrawables(), game.getHangingEnemies());

		controllers = new ArrayList<>();
		controllers.add(new PlayerController(game.getPlayer()));
		game.getWalkingEnemies().forEach(e -> controllers.add(new WalkingEnemyController(e)));
		game.getHangingEnemies().forEach(e -> controllers.add(new HangingEnemyController(e)));

	}


	@Override
	public void render (float delta) {
		game.update(delta);
		for (Controller c : controllers) {
			c.update();
		}
		renderer.render();
	}

	@Override
	public void show() {

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
