package soldater.johannas.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import soldater.johannas.control.menu.GameMenu;
import soldater.johannas.view.GameRenderer;
import soldater.johannas.view.ScreenRenderer;
import soldater.johannas.view.UIRenderer;

import java.util.ArrayList;
import java.util.List;

public class GameScreen extends ScreenRenderer {

	private final GameMenu gameMenu;
	private GameRenderer gameRenderer;
	private UIRenderer uiRenderer;
	private soldater.johannas.model.Game game;

	private List<Controller> controllers;

	// TODO: Move this to an audio controller!
    private Sound eatSound  = Gdx.audio.newSound(Gdx.files.internal("sounds/eat.wav"));
    private Sound dmgSound = Gdx.audio.newSound(Gdx.files.internal("sounds/damage.wav"));
    private Sound dieSound = Gdx.audio.newSound(Gdx.files.internal("sounds/died.wav"));
	private int lastTakenLunchBoxes = 0;
	private int lastHealth = 4;

	private boolean paused = false;

	public GameScreen(GameMenu levelSection, String level) {
		this.gameMenu = levelSection;

		System.out.println(level);
		// Initialize the world!
		game = new soldater.johannas.model.Game();
		game.startGame(level);

		gameRenderer = new GameRenderer(game);
		uiRenderer = new UIRenderer(game, gameMenu);

		controllers = new ArrayList<>();
		controllers.add(new PlayerController(game.getPlayer()));
		game.getWalkingEnemies().forEach(e -> controllers.add(new WalkingEnemyController(e)));
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
        }

        if (game.getTakenLunchBoxes() > lastTakenLunchBoxes) {
	        lastTakenLunchBoxes = game.getTakenLunchBoxes();
            eatSound.play();
        }

        if (game.getPlayer().getLife() < lastHealth) {
	        lastHealth = game.getPlayer().getLife();
	        dmgSound.play();
        }

        if (game.getPlayer().getLife() == 0) {
            dieSound.play();
        }

		uiRenderer.render(delta);
	}

	@Override
	public void dispose () {
		gameRenderer.dispose();
	}
}
