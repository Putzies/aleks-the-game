package soldater.johannas.view;

import java.util.List;

/**
 * Class for serializing and deserializing meta info of a level
 */
public class LevelInfo {
    private String name;
    private List<Highscore> highScores;

    public String getName() {
        return name;
    }

    public List<Highscore> getHighscores() {
        return highScores;
    }
}
