package soldater.johannas.view.modal;

import soldater.johannas.control.menu.GameMenu;
import soldater.johannas.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class WonModalMenu extends ModalMenu {
    public WonModalMenu(GameMenu gameMenu) {
        super(gameMenu);

        addItems();
    }

    @Override
    protected void enter() {
        switch (selectedItem) {
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
        items.add(new MenuItem("menu/nextLevel.png", MenuItem.Alignment.LEFT));
        items.add(new MenuItem("menu/replay.png", MenuItem.Alignment.LEFT));
        items.add(new MenuItem("menu/exit.png", MenuItem.Alignment.LEFT));
        setItems(items);
        setTitle(new MenuItem("menu/won.png"));
    }
}
