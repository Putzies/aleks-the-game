package soldater.johannas.view;

import soldater.johannas.util.Timer;

public class Highscore {
    private int score;
    private String name;

    public Highscore() {

    }

    public Highscore(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public String getFormattedText() {
        return Timer.getFormattedTime(score) + " -- " + name;
    }
}
