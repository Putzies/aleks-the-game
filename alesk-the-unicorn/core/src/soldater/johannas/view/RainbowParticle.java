package soldater.johannas.view;

import java.util.Random;

public class RainbowParticle {

    private final int width = 3;
    private final int height = 60;
    private double x, y;
    private Random rand;
    private double timeExisted = 0;
    private static final double lifetime = 30;

    private int fadeLevel;
    private double randPos;

    public RainbowParticle(double x, double y) {
        this.x = x;
        this.y = y;
        rand = new Random();
    }

    public void update(double dTime) {

        double lifePart = (timeExisted/lifetime)*10;

        if (lifePart > 5) {
            randPos = rand.nextInt((int)Math.pow(lifePart-4, 2.2));
            fadeLevel = (int) ((lifePart-5)*1.4) + rand.nextInt(4);
        }

        timeExisted += dTime;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y - randPos;
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