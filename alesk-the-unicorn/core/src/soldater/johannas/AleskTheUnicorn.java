package soldater.johannas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import soldater.johannas.control.GameScreen;
import soldater.johannas.control.menu.GameMenu;
import soldater.johannas.control.menu.LevelSelection;
import soldater.johannas.control.menu.MainMenu;
import soldater.johannas.view.LevelInfo;
import soldater.johannas.view.LevelSelectRenderer;
import soldater.johannas.view.MainMenuRenderer;

import java.util.ArrayList;
import java.util.List;


public class AleskTheUnicorn extends Game implements MainMenu, LevelSelection, GameMenu {

	private Screen screen;
	private String lastLevel;
	private String nextLevel = "";

	@Override
	public void create() {
        screen = new MainMenuRenderer(this);
        this.setScreen(screen);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void startLevel(String level) {
	    lastLevel = level;
		screen.dispose();
	    screen = new GameScreen(this, level);
		setScreen(screen);
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
	    screen = new LevelSelectRenderer(this, loadLevels());
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

	private List<LevelInfo> loadLevels() {
		List<LevelInfo> levels = new ArrayList<>();

		FileHandle[] internalFiles = Gdx.files.local("levels/").list();

		for(FileHandle file : internalFiles) {
			if(file.nameWithoutExtension().endsWith(".meta")) {
				levels.add(new LevelInfo(file));
			}
		}

		return levels;
	}

	@Override
	public void exitLevel() {
		screen.dispose();
		screen = new LevelSelectRenderer(this, loadLevels());
		setScreen(screen);
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
}
