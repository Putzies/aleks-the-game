package soldater.johannas.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import soldater.johannas.model.Movable;

public class PlayerController {
    private Movable player;

    public PlayerController(Movable player) {
        this.player = player;
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.right();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.left();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.jump();
        }
    }
}
