package soldater.johannas.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import soldater.johannas.control.menu.GameMenu;
import soldater.johannas.model.DrawableGame;
import soldater.johannas.view.modal.LostModalMenu;
import soldater.johannas.view.modal.ModalMenu;
import soldater.johannas.view.modal.PauseModalMenu;
import soldater.johannas.util.Colors;
import soldater.johannas.view.modal.WonModalMenu;

public class UIRenderer {

    private final int TOP_Y = Gdx.graphics.getHeight() * 19 / 20;

    private DrawableGame game;
    private GameMenu gameMenu;

    private ModalMenu modalMenu;

    private SpriteBatch batch;
    private BitmapFont font;
    private GlyphLayout layout;

    private Texture horn;


    public UIRenderer(DrawableGame game, GameMenu gameMenu) {
        this.game = game;
        this.gameMenu = gameMenu;

        font = new BitmapFont();
        font.getData().setScale(2);
        layout = new GlyphLayout(font, "", Colors.MENU_COLOR, 0, Align.center, true);
        batch = new SpriteBatch();

        loadTextures();
    }

    public void render(float delta) {
        batch.begin();

        checkInput();

        renderHorn();
        renderTimer();
        renderScore();


        if (modalMenu != null) {
            modalMenu.render(batch, delta);
        }

        batch.end();
    }

    public void resume() {
        modalMenu = null;
    }

    private void renderHorn() {
        int width = horn.getWidth()/4;
        int height = horn.getHeight();
        batch.draw(horn,
                20,
                TOP_Y - height,
                (game.getPlayer().getLife()-1)*width,
                0,
                width,
                height
        );
    }

    private void renderTimer() {
        layout.setText(font, game.getTimer().getFormattedTime());
        font.draw(batch, layout, Gdx.graphics.getWidth() / 2 - layout.width / 2, TOP_Y);
    }

    private void renderScore() {
        layout.setText(font,game.getTakenLunchBoxes() + " / " + game.getTotalLunchBoxes());
        font.draw(batch, layout, Gdx.graphics.getWidth() - 80, TOP_Y);
    }

    private void checkInput() {
        if (game.getTakenLunchBoxes() == game.getTotalLunchBoxes() && modalMenu == null) {
            gameMenu.pause();
            modalMenu = new WonModalMenu(gameMenu);
        }

        if (game.getPlayer().getLife() == 0 && modalMenu == null) {
            gameMenu.pause();
            modalMenu = new LostModalMenu(gameMenu);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gameMenu.pause();
            modalMenu = new PauseModalMenu(gameMenu);
        }
    }

    private void loadTextures() {
        horn = new Texture("horn.png");
    }
}
