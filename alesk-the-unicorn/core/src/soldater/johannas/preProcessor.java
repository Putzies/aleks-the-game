package soldater.johannas;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import soldater.johannas.model.level.Block;

import java.util.ArrayList;
import java.util.List;

public class preProcessor {
    public preProcessor(){}

    public BoundingBox generateConnected(){
        List<BoundingBox> bList = new ArrayList<>();


            Vector3 min = new Vector3(-500,0,0);
            Vector3 max = new Vector3(500,Block.WIDTH,0);

            Vector3 min2 = new Vector3(10,10,0);
            Vector3 max3 = new Vector3(30,30,0);

            BoundingBox bBox2 = new BoundingBox(min,max);
            BoundingBox bBox  = new BoundingBox(min2,max3);

            System.out.println(bBox.intersects(bBox2));



        return bBox2;


    }
}
