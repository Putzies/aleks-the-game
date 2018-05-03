package soldater.johannas.model;

public class AABB {
    protected double x, y;
    // The width and height of the bounding box does not change
    protected final int WIDTH;
    protected final int HEIGHT;


    AABB(double x, double y, int width, int height){
        this.x = x;
        this.y = y;

        this.WIDTH  = width;
        this.HEIGHT = height;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    // Check if intersecting in both X-Axis and Y-Axis
    public boolean intersects(AABB other){
        return isWithinX(other) && isWithinY(other);
    }


    // Check for intersection in X-Axis
    private boolean isWithinX(AABB other) {
        boolean withinX = this.x + this.WIDTH > other.x &&
                this.x < other.x + other.WIDTH;

        return withinX;
    }

    // Check for intersection in Y-Axis
    private boolean isWithinY(AABB other) {
        boolean withinY = this.y + this.HEIGHT > other.y &&
                this.y + 1 < other.y + other.HEIGHT;

        return withinY;
    }

}
