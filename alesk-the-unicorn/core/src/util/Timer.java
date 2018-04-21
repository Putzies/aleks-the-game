package util;

public class Timer {
    private int millis = 0;

    public void update(float delta) {
        millis += delta * 1000;
    }

    public int getMillis() {
        return millis;
    }

    public int getHundreds() {
        return millis / 10;
    }

    public int getSeconds() {
        return millis / 1000;
    }

    public int getMinutes() {
        return getSeconds() / 60;
    }

    public int getHours() {
        return getMinutes() / 60;
    }

    public String getFormattedTime() {
        String str = getHours() + ":" + getMinutes() + ":" + getSeconds() + ":" + getHundreds();
        String[] split = str.split(":");
        for (int i = 0; i < split.length; i++) {
            if(split[i].length() == 1) {
                split[i] = "0" + split[i];
            }
        }

        return String.join(":", split);
    }
}
