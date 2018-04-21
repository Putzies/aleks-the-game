package soldater.johannas.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import soldater.johannas.control.menu.GameMenu;
import soldater.johannas.view.Renderer;
import soldater.johannas.view.ScreenRenderer;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenRenderer {

	private final GameMenu gameMenu;
	private Renderer renderer;
	private soldater.johannas.model.Game game;

	private List<Controller> controllers;

	public GameScreen(GameMenu levelSection, String level) {
		this.gameMenu = levelSection;

		System.out.println(level);
		// Initialize the world!
		game = new soldater.johannas.model.Game();
		game.startGame(level);

		renderer = new Renderer(game);

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

		if (game.getTakenLunchBoxes() == game.getTotalLunchBoxes()) {
			System.out.println("You won! Your time: " + game.getTimer().getFormattedTime());
			gameMenu.exitLevel();
		}

		if (game.getPlayer().getLife() == 0) {
			System.out.println("You lost! You didn't eat all the lunchboxes!");
			gameMenu.exitLevel();
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			gameMenu.exitLevel();
		}
	}

	@Override
	public void dispose () {
		renderer.dispose();
	}
}
