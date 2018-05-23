package soldater.johannas.testing;


import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import soldater.johannas.model.*;

public class BoundingBoxTestUnit extends TestCase {
    AABB a,b,c;

    @Before
    public void setUp(){
        a = new AABB(0,0,40,40);
        b = new AABB(50, 50, 100, 100);
        c = new AABB(20,-39.5,50,40);
    }



    @Test
    public void testGetters() {
        // Tests the different getters, any double requires a third parameter delta for error precision.
        assertEquals(0, a.getX(),0);
        assertEquals(0, a.getY(),0);
        assertEquals(40,a.getWidth());
        assertEquals(40,a.getHeight());
    }

    @Test
    public void testIsWithin(){
        // Tests if a,b are not intersecting.
        assertEquals(false, a.intersects(b));

        // Tests if a,c are intersecting
        assertEquals(true, a.intersects(c));
    }

    @Test
    public void testLookAheadY(){
        // A is the player, so he is standing on some block. c is a block that the player sees in the distance
        // Check if "A is on the same elevation as C"
        assertEquals(true, a.lookaheadY(c));

        // Check if "A is on the same elevation as B"
        assertEquals(false, a.lookaheadY(b));

    }
}
