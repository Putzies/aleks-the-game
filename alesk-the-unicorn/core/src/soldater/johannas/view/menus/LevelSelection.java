package soldater.johannas.view.menus;

/**
 * Interface used by the LevelSelectRenderer to go back or start a level.
 */
public interface LevelSelection {
    void startLevel(String level);
    void goBack();
}
