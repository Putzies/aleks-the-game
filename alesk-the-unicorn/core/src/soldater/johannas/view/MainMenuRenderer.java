package soldater.johannas.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import soldater.johannas.control.menu.MainMenu;

import java.awt.event.KeyListener;

public class MainMenuRenderer implements Screen {
    private MainMenu mainMenu;
    private SpriteBatch batch;

    private Stage stage;
    private Table table;

    public MainMenuRenderer(MainMenu menu) {
        this.mainMenu = menu;
        batch = new SpriteBatch();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.setDebug(false);

        addButtons();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.2f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void addButtons() {

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        //buttonStyle.up = new TextureRegionDrawable(upRegion);
        //buttonStyle.down = new TextureRegionDrawable(downRegion);
        buttonStyle.font = new BitmapFont();
        buttonStyle.font.getData().setScale(4);

        TextButton btnStartGame = new TextButton("Start Game", buttonStyle);
        TextButton btnQuitGame = new TextButton("Quit Game", buttonStyle);

        btnStartGame.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                mainMenu.startGame();
            }
        });

        btnQuitGame.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                mainMenu.quitGame();
            }
        });

        table.add(btnStartGame);
        table.row();
        table.add(btnQuitGame);
    }
}
