package soldater.johannas.view.modal;

import soldater.johannas.view.menus.GameMenu;
import soldater.johannas.view.menus.MenuItem;

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
            case 1:
                gameMenu.replay();
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
        setTitle(new MenuItem("menu/paused.png"));
    }
}
