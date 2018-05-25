package soldater.johannas.view.modal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import soldater.johannas.view.menus.GameMenu;
import soldater.johannas.view.menus.AnimatedMenu;
import soldater.johannas.view.menus.MenuItem;

import java.util.ArrayList;
import java.util.List;

public abstract class ModalMenu extends AnimatedMenu {

    protected GameMenu gameMenu;
    protected int selectedItem = 0;

    private List<MenuItem> items;
    private MenuItem title;
    private Texture background;
    private int margin = 0;
    int modalX, modalY;

    public ModalMenu(GameMenu gameMenu) {
        loadTextures();
        this.gameMenu = gameMenu;
        modalX = (Gdx.graphics.getWidth() - background.getWidth()) / 2;
        modalY = (Gdx.graphics.getHeight() - background.getHeight()) / 2;
        items = new ArrayList<>();
    }

    public void render(SpriteBatch batch, float delta) {
        Gdx.gl.glClear(GL20.GL_ACTIVE_TEXTURE);

        checkInput();
        incrementFrames(delta);

        batch.draw(background, modalX, modalY);

        renderItems(batch);
    }

    protected void setTitle(MenuItem title) {
        this.title = title;
    }

    protected void setItems(List<MenuItem> items) {
        this.items = items;

        int totalItemWidth = 0;
        for(MenuItem i : items) {
            totalItemWidth += i.getWidth();
        }

        margin = (background.getWidth() - totalItemWidth) / (items.size() + 1);
    }

    protected void checkInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            selectedItem = selectedItem - 1;
            if (selectedItem < 0) {
                selectedItem += items.size();
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            selectedItem = (selectedItem + 1) % items.size();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            enter();
        }
    }

    protected void enter() {

    }

    private void renderItems(SpriteBatch batch) {
        int x = margin;

        title.draw(batch, Gdx.graphics.getWidth() / 2, modalY + background.getHeight() * 15 / 20);

        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);

            int offsetX = 0;
            int offsetY = 0;

            if (i == selectedItem) {
                offsetX = (int)(Math.sin(((double) frame / N_FRAMES) * (Math.PI * 2)) * 3);
                offsetY = (int)(Math.cos(((double) frame / N_FRAMES) * (Math.PI * 2)) * 5);
            }

            item.draw(
                    batch, x + offsetX + modalX,
                    (background.getHeight() / 2) - (item.getHeight() * 2 / 3) + offsetY + modalY
            );
            x += item.getWidth() + margin;
        }
    }

    private void loadTextures() {
        background = new Texture("menu/modalMenuBackground.png");
    }
}
