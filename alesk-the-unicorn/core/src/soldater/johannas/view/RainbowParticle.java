package soldater.johannas.view;

public class RainbowParticle {

    private final int width = 6;
    private final int height = 60;
    private double x, y;
    private double timeExisted = 0;
    private static final double lifetime = 50;

    private int fadeLevel;

    public RainbowParticle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void update(double dTime) {
        timeExisted += dTime;
        if(timeExisted < 2 || timeExisted > 40 ) {
            fadeLevel = 1;
        } else if (timeExisted < 1 || timeExisted > 45) {
            fadeLevel = 2;
        } else {
            fadeLevel = 0;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean timeToDie() {
        return timeExisted > lifetime;
    }

    public int getFadeLevel() {
        return fadeLevel;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
