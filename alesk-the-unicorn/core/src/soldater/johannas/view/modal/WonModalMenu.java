package soldater.johannas.view.modal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.control.menu.GameMenu;
import soldater.johannas.util.Colors;
import soldater.johannas.util.Parser;
import soldater.johannas.util.Timer;
import soldater.johannas.view.Highscore;
import soldater.johannas.view.menus.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class WonModalMenu extends ModalMenu {

    private final int HIGHSCORE_X = Gdx.graphics.getWidth() / 2;

    private int score;
    private String formattedScore;
    private Parser parser;

    private boolean willExit = false;
    private boolean isHighscore;

    public WonModalMenu(GameMenu gameMenu, Timer gameTimer) {
        super(gameMenu);
        parser = new Parser();
        score = gameTimer.getMillis();
        formattedScore = gameTimer.getFormattedTime();
        List<Highscore> highscores = parser.loadLevelInfo(gameMenu.getLevelName()).getHighscores();
        isHighscore = highscores.size() == 0 || highscores.get(0).getScore() > score;

        addItems();
    }

    @Override
    protected void enter() {
        switch (selectedItem) {
            case 0:
                gameMenu.nextLevel();
            case 1:
                gameMenu.replay();
                break;
            case 2:
                gameMenu.exitLevel();
                break;
        }
    }

    @Override
    public void render(SpriteBatch batch, float delta) {
        if (isHighscore) {
            Gdx.input.getTextInput(
                    new Input.TextInputListener() {
                        @Override
                        public void input(String text) {
                            willExit = true;
                            parser.saveHighscore(score, gameMenu.getLevelName(), text);
                            isHighscore = false;
                        }

                        @Override
                        public void canceled() {
                            willExit = true;
                        }
                    },
                    "New high score!",
                    "Anonymous",
                    "Your name");
            isHighscore = false;
        }

        if (willExit) {
            gameMenu.exitLevel();
            return;
        }

        super.render(batch, delta);
    }

    private void addItems() {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("menu/nextLevel.png", MenuItem.Alignment.LEFT));
        items.add(new MenuItem("menu/replay.png", MenuItem.Alignment.LEFT));
        items.add(new MenuItem("menu/exit.png", MenuItem.Alignment.LEFT));
        setItems(items);
        setTitle(new MenuItem("menu/won.png"));
    }
}
