package soldater.johannas;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.view.GameScreen;
import soldater.johannas.view.LevelSelectScreen;


public class AleskTheUnicorn extends Game {

	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new LevelSelectScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
