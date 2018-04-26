package soldater.johannas.view.modal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import soldater.johannas.control.menu.GameMenu;
import soldater.johannas.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class PauseModalMenu extends ModalMenu {

    public PauseModalMenu(GameMenu gameMenu) {
        super(gameMenu);

        addItems();
    }

    @Override
    protected void enter() {
        switch (selectedItem) {
            case 0:
                gameMenu.resume();
                break;
            case 2:
                gameMenu.exitLevel();
                break;
        }
    }

    private void addItems() {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("menu/resume.png", MenuItem.Alignment.LEFT));
        items.add(new MenuItem("menu/replay.png", MenuItem.Alignment.LEFT));
        items.add(new MenuItem("menu/exit.png", MenuItem.Alignment.LEFT));
        setItems(items);
    }
}
