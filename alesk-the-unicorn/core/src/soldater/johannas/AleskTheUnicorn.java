package soldater.johannas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import soldater.johannas.control.GameController;
import soldater.johannas.control.menu.LevelSelection;
import soldater.johannas.control.menu.MainMenu;
import soldater.johannas.view.LevelInfo;
import soldater.johannas.view.LevelSelector;

import java.util.ArrayList;
import java.util.List;


public class AleskTheUnicorn extends Game implements MainMenu, LevelSelection {

    private LevelSelector levelSelector;

	@Override
	public void create() {
        levelSelector = new LevelSelector(this, loadLevels());
		this.setScreen(levelSelector);
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
	public void startGame() {



	}

	@Override
	public void settings() {

	}

	@Override
	public void quitGame() {

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
