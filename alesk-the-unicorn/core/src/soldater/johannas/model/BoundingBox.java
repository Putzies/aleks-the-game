package soldater.johannas.model;

public class BoundingBox {
    protected double x, y;
    // The width and height of the bounding box does not change
    protected final int WIDTH;
    protected final int HEIGHT;


    public BoundingBox(double x, double y, int width, int height){
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
    public boolean intersects(BoundingBox other){
        return isWithinX(other) && isWithinY(other);
    }

    // Not the smartest of functions but saves space.
    public boolean lookaheadY(BoundingBox box2){
        return Math.abs(this.getY() - (box2.getY() + box2.getHeight()-1)) < 1 &&
                Math.abs(this.getY() - (box2.getY() + box2.getHeight()-1)) > 0;
    }

    // Check for intersection in X-Axis
    private boolean isWithinX(BoundingBox other) {
        boolean withinX = this.x + this.WIDTH > other.x &&
                this.x < other.x + other.WIDTH;

        return withinX;
    }

    // Check for intersection in Y-Axis
    private boolean isWithinY(BoundingBox other) {
        boolean withinY = this.y + this.HEIGHT > other.y &&
                this.y  < other.y + other.HEIGHT;

        return withinY;
    }


}
