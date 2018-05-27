package soldater.johannas.view.menus;

/**
 * Interface for access to the game menu, used by the modal menus to resume the game or go to the next level etc.
 */
public interface GameMenu {
    void exitLevel();
    void pause();
    void resume();
    void replay();
    void nextLevel();
    String getLevelName();
}
