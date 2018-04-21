package soldater.johannas.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.control.menu.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class MainMenuRenderer extends ScreenRenderer {

    private final int ITEM_WIDTH = 225;
    private int START_Y = Gdx.graphics.getHeight() / 3;
    private final int MARGIN = 40;

    private MainMenu mainMenu;
    private SpriteBatch batch;

    private Texture textBackground;
    private List<MenuItem> items = new ArrayList<>();

    private int selectItem = 0;

    public MainMenuRenderer(MainMenu menu) {
        this.mainMenu = menu;
        batch = new SpriteBatch();

        loadTextures();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        checkInput();

        batch.begin();

        batch.draw(
                textBackground,
                0,
                0,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        );

        renderItems();

        batch.end();
    }

    private void loadTextures() {
        textBackground = new Texture("menu/mainMenuBackground.png");

        items.add(new MenuItem("menu/startGame.png"));
        items.add(new MenuItem("menu/quitGame.png"));
    }

    private void renderItems() {
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);

            if (i == selectItem) {
                renderSelected();
            } else {
                item.draw(
                        batch, Gdx.graphics.getWidth() / 2,
                        START_Y - (item.getHeight() + MARGIN) * i
                );
            }
        }
    }

    private void renderSelected() {
        MenuItem item = items.get(selectItem);

        int offsetX = (int)(Math.sin(((double) frame / N_FRAMES) * (Math.PI * 2)) * 3);
        int offsetY = (int)(Math.cos(((double) frame / N_FRAMES) * (Math.PI * 2)) * 5);

        item.draw(
                batch,
                Gdx.graphics.getWidth() / 2 + offsetX,
                START_Y - (item.getHeight() + MARGIN) * selectItem + offsetY
                );
    }

    private void checkInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectItem = selectItem - 1;
            if (selectItem < 0) {
                selectItem += items.size();
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectItem = (selectItem + 1) % items.size();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            enter();
        }
    }

    private void enter() {
        switch (selectItem) {
            case 0: // Start game
                mainMenu.startGame();
                break;
            case 1: // Quit game
                mainMenu.quitGame();
                break;
        }
    }
}
