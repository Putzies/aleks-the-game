package soldater.johannas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import soldater.johannas.control.GameController;
import soldater.johannas.control.menu.LevelSelection;
import soldater.johannas.control.menu.MainMenu;
import soldater.johannas.view.LevelInfo;
import soldater.johannas.view.LevelSelector;
import soldater.johannas.view.MainMenuRenderer;

import java.util.ArrayList;
import java.util.List;


public class AleskTheUnicorn extends Game implements MainMenu, LevelSelection {

    private MainMenuRenderer mainMenu;
    private LevelSelector levelSelector;

	@Override
	public void create() {
        mainMenu = new MainMenuRenderer(this);
        levelSelector = new LevelSelector(this, loadLevels());
        this.setScreen(mainMenu);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void startLevel(String level) {
		setScreen(new GameController(this, level));
		levelSelector.dispose();
	}

	@Override
	public boolean startGame() {
	    mainMenu.dispose();
        this.setScreen(levelSelector);
        return true;
	}

	@Override
	public void settings() {

	}

	@Override
	public void quitGame() {
	    mainMenu.dispose();
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
}
