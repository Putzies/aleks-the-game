package soldater.johannas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import soldater.johannas.control.GameScreen;
import soldater.johannas.control.SoundController;
import soldater.johannas.control.menu.GameMenu;
import soldater.johannas.control.menu.LevelSelection;
import soldater.johannas.control.menu.MainMenu;
import soldater.johannas.util.Parser;
import soldater.johannas.view.LevelInfo;
import soldater.johannas.view.menus.LevelSelectRenderer;
import soldater.johannas.view.menus.MainMenuRenderer;

import java.util.List;


public class AleskTheUnicorn extends Game implements MainMenu, LevelSelection, GameMenu {

	private Screen screen;
	private String lastLevel;
    private SoundController soundController;
    private Parser parser;
    private List<LevelInfo> levels;

	@Override
	public void create() {
	    parser = new Parser();
		levels = parser.loadLevels();

        screen = new MainMenuRenderer(this);
        this.setScreen(screen);
        soundController = new SoundController();
        soundController.loopTheme();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void startLevel(String level) {
	    lastLevel = level;
		screen.dispose();
	    screen = new GameScreen(this, level, soundController);
		setScreen(screen);
		soundController.stopTheme();
	}

	@Override
	public void goBack() {
	    screen.dispose();
	    screen = new MainMenuRenderer(this);
	    setScreen(screen);
	}

	@Override
	public boolean startGame() {
	    screen.dispose();
	    screen = new LevelSelectRenderer(this, levels);
	    setScreen(screen);
        return true;
	}

	@Override
	public void settings() {

	}

	@Override
	public void quitGame() {
	    screen.dispose();
        Gdx.app.exit();
	}

	@Override
	public void exitLevel() {
		screen.dispose();
		levels = parser.loadLevels();
		screen = new LevelSelectRenderer(this, levels);
		setScreen(screen);
        soundController.loopTheme();
	}

	@Override
    public void pause() {
	    screen.pause();
    }

    @Override
    public void resume() {
	    screen.resume();
    }

	@Override
	public void replay() {
		startLevel(lastLevel);
	}

	@Override
	public void nextLevel() {
	    String nextLevel = "";

	    // Find next level
	    List<LevelInfo> allLevels = levels;
	    for (int i = 0; i < allLevels.size() - 1; i++) {
	        if (allLevels.get(i).getName().equals(lastLevel)) {
	            nextLevel = allLevels.get(i + 1).getName();
            }
        }

		if (nextLevel.isEmpty()) {
			exitLevel();
		} else {
		    startLevel(nextLevel);
        }
	}

    @Override
    public String getLevelName() {
        return lastLevel;
    }
}
