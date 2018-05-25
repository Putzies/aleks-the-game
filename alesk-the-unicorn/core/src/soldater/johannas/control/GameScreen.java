package soldater.johannas.control;

import soldater.johannas.view.menus.GameMenu;
import soldater.johannas.view.game.GameRenderer;
import soldater.johannas.view.menus.ScreenRenderer;
import soldater.johannas.view.game.UIRenderer;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenRenderer {

	private final GameMenu gameMenu;
	private GameRenderer gameRenderer;
	private UIRenderer uiRenderer;
	private soldater.johannas.model.Game game;

	private List<Controller> controllers;
	private SoundController soundController;

	private boolean paused = false;

	public GameScreen(GameMenu levelSection, String level, SoundController soundController) {
		this.gameMenu = levelSection;
		this.soundController = soundController;
		soundController.reset();

		System.out.println(level);
		// Initialize the world!
		game = new soldater.johannas.model.Game();
		game.startGame(level);

		gameRenderer = new GameRenderer(game);
		uiRenderer = new UIRenderer(game, gameMenu);

		controllers = new ArrayList<>();
		controllers.add(new PlayerController(game.getPlayer(), soundController));
		game.getWalkingEnemies().forEach(e -> controllers.add(new WalkingEnemyController(e, game.getPlayer(), soundController)));
		game.getHangingEnemies().forEach(e -> controllers.add(new HangingEnemyController(e)));

	}

	@Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
	    paused = false;
	    uiRenderer.resume();
    }

	@Override
	public void render (float delta) {

	    if (!paused) {
            game.update(delta);
            for (Controller c : controllers) {
                c.update();
            }
            gameRenderer.render();

            soundController.update(game);
        }

		uiRenderer.render(delta);
	}

	@Override
	public void dispose () {
		gameRenderer.dispose();
	}
}
