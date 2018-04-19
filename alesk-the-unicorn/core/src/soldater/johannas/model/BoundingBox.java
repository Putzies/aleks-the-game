package soldater.johannas.model;

public class BoundingBox {
    protected double x,y;
    protected double cX, cY;
    // The width and height of the bounding box does not change
    protected final double width, height;
    protected final double halfSizeX, halfSizeY;

    BoundingBox(double x, double y, double width, double height){
        this.x = x;
        this.y = y;

        this.width  = width;
        this.height = height;

        this.cX = (x + width)  / 2;
        this.cY = (y + height) / 2;

        this.halfSizeX = width / 2;
        this.halfSizeY = width / 2;
    }


    public boolean intersects(BoundingBox other){
        boolean intersect = false;

        // To be filled out.



        return intersect;
    }

}
