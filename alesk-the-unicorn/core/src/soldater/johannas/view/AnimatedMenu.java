package soldater.johannas.view;

public class AnimatedMenu {
    protected final int N_FRAMES = 10;
    protected int frame;
    protected float frameCounter = 0;

    protected void incrementFrames(float delta) {
        frameCounter += delta;

        if (frameCounter > 0.03) {
            frame = (frame + 1) % N_FRAMES;
            frameCounter = 0;
        }
    }
}
