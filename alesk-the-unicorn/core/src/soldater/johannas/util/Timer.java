package soldater.johannas.util;

public class Timer {
    private int millis = 0;

    public void update(float delta) {
        millis += delta * 1000;
    }

    public int getMillis() {
        return millis;
    }

    public static int getHundreds(int millis) {
        return millis / 10;
    }

    public static int getSeconds(int millis) {
        return millis / 1000;
    }

    public static int getMinutes(int millis) {
        return getSeconds(millis) / 60;
    }

    public static int getHours(int millis) {
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
