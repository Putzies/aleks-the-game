package soldater.johannas.model;

public class CollisionObj {
    int location;
    boolean value;
    double correction;
    double sizeCorrection;

    CollisionObj(int location, boolean value, double correction) {
        this.location = location;
        this.value    = value;
        this.correction = correction;
    }

    CollisionObj(int location, boolean value, double correction, double sizeCorrection) {
        this.location       = location;
        this.value          = value;
        this.correction     = correction;
        this.sizeCorrection = correction;
    }
}

/*

// TODO Check the colls.
if (colls.size() == 2) {
    System.out.println(" "+ colls.get(0).location + " :: " + colls.get(1).location );

if ((colls.get(0).location == Character.DOWN) && (colls.get(1).location == Character.LEFT)) {
    System.out.println("Triggered");
    System.out.print("");
    character.setCollision(Character.DOWN, true, colls.get(0).correction);
    character.setCollision(Character.LEFT, true, colls.get(1).correction);
}

}

ArrayList<CollisionObj> colls = new ArrayList<CollisionObj>();

colls.add(new CollisionObj(Character.LEFT, true, block.getX() + block.getWidth() + 1));
colls.add(new CollisionObj(Character.UP, true, block.getY() - character.getHeight()));
colls.add(new CollisionObj(Character.RIGHT, true, block.getX() - character.getWidth()));
colls.add(new CollisionObj(Character.DOWN, true, block.getY(), block.getHeight()-1));


            Vector3 min = new Vector3(0,0,0);
            Vector3 max = new Vector3(10,10,0);

            Vector3 min2 = new Vector3(10,10,0);
            Vector3 max3 = new Vector3(30,30,0);

            BoundingBox bBox2 = new BoundingBox(min,max);
            BoundingBox bBox  = new BoundingBox(min2,max3);

            System.out.println(bBox.intersects(bBox2));

            */



