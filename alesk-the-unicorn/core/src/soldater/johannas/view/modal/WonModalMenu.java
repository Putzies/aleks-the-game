package soldater.johannas.view.modal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.view.menus.GameMenu;
import soldater.johannas.service.Parser;
import soldater.johannas.util.Timer;
import soldater.johannas.view.Highscore;
import soldater.johannas.view.menus.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Rendering the modal menu when won. Prompts to save high score if there is a new high score.
 */
public class WonModalMenu extends ModalMenu {

    private int score;
    private String formattedScore;
    private Parser parser;
    private boolean showHighscore;

    public WonModalMenu(GameMenu gameMenu, Timer gameTimer) {
        super(gameMenu);
        parser = new Parser();
        score = gameTimer.getMillis();
        formattedScore = gameTimer.getFormattedTime();
        List<Highscore> highscores = parser.loadLevelInfo(gameMenu.getLevelName()).getHighscores();
        showHighscore = highscores.size() == 0 || highscores.get(0).getScore() > score;

        addItems();

        System.out.println("This is the constructor of the WonModalMenu.");
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
        super.render(batch, delta);

        if (showHighscore) {
            popupHighscore();
            while(showHighscore) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted!");
                }
            }
        }
    }

    private void addItems() {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("menu/nextLevel.png", MenuItem.Alignment.LEFT));
        items.add(new MenuItem("menu/replay.png", MenuItem.Alignment.LEFT));
        items.add(new MenuItem("menu/exit.png", MenuItem.Alignment.LEFT));
        setItems(items);
        setTitle(new MenuItem("menu/won.png"));
    }

    private void popupHighscore() {
        Gdx.input.getTextInput(
                new Input.TextInputListener() {
                    @Override
                    public void input(String text) {
                        showHighscore = false;
                        parser.saveHighscore(score, gameMenu.getLevelName(), text);
                    }

                    @Override
                    public void canceled() {
                        showHighscore = false;
                    }
                },
                "New high score!",
                "Anonymous",
                "Your name");
    }
}
