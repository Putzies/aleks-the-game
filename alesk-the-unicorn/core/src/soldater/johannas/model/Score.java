package soldater.johannas.model;

import sun.util.resources.LocaleData;

import java.time.LocalDateTime;
import java.util.Locale;

public class Score {
    // Probably not an int, instead date or smth.
    public final int time;
    public final String name;
    public final LocalDateTime date;

    public Score(int time, String name){
        this.time = time;
        this.name = name;
        date = LocalDateTime.now();
    }
}
