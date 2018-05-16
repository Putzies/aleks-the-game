package soldater.johannas.control.menu;

public interface GameMenu {
    void exitLevel();
    void pause();
    void resume();
    void replay();
    void nextLevel();
    String getLevelName();
}
