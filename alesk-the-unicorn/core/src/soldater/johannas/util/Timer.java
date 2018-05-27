package soldater.johannas.util;

/**
 * Class for keeping track of the time in milliseconds, such as the score of the player in a level.
 * Can also format the time nicely into a string. This class can be reused in a completely different context.
 */
public class Timer {
    private int millis = 0;

    public void update(float delta) {
        millis += delta * 1000;
    }

    public int getMillis() {
        return millis;
    }

    private static int getHundreds(int millis) {
        return (millis / 10) % 100;
    }

    private static int getSeconds(int millis) {
        return (millis / 1000) % 60;
    }

    private static int getMinutes(int millis) {
        return getSeconds(millis) / 60;
    }

    private static int getHours(int millis) {
        return getMinutes(millis) / 60;
    }

    public static String getFormattedTime(int millis) {
        String str = getHours(millis) + ":" + getMinutes(millis) + ":" + getSeconds(millis) + ":" + getHundreds(millis);
        String[] split = str.split(":");
        for (int i = 0; i < split.length; i++) {
            if(split[i].length() == 1) {
                split[i] = "0" + split[i];
            }
        }

        return String.join(":", split);
    }

    public String getFormattedTime() {
        return getFormattedTime(millis);
    }
}
