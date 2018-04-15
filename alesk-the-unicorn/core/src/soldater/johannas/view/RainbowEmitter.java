package soldater.johannas.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RainbowEmitter {

    private List<RainbowParticle> rainbow;
    private Texture texture = new Texture("rainbow.png");
    private int width = 3;

    public RainbowEmitter(){
        rainbow = new ArrayList<RainbowParticle>();
    }

    public List<RainbowParticle> getRainbow() {
        return rainbow;
    }

    public void update(double dTime, double x, double y, boolean add) {
        if (add) {
            addParticles(x, y);
        }
        deleteParticles(dTime);
    }

    private void addParticles(double x, double y) {
        if (rainbow.size() > 0) {
            double oldX = rainbow.get(rainbow.size()-1).getX();
            double oldY = rainbow.get(rainbow.size()-1).getY();
            double xVel = roundTo3(x - oldX);
            double yVel = (y - oldY);
            System.out.println(xVel);
            int z = (int)Math.abs(xVel)/width;
            for(double i = width; i <= xVel; i += width) {
                rainbow.add(new RainbowParticle(x-(xVel-i), y - ((xVel-i)/3)*(yVel)/z));
            }
            for(double i = width; i <= -xVel; i += width) {
                rainbow.add(new RainbowParticle(x+(-xVel-i), y - ((-xVel-i)/3)*(yVel)/z));
            }
        } else {
            rainbow.add(new RainbowParticle(x, y));
        }
    }

    private void deleteParticles(double dTime) {
        for (Iterator<RainbowParticle> iterator = rainbow.iterator(); iterator.hasNext();) {
            RainbowParticle particle = iterator.next();
            particle.update(dTime);
            if (particle.timeToDie()) {
                iterator.remove();
            }
        }
    }

    public void draw(SpriteBatch batch, int xOffset, int yOffset) {

            for(RainbowParticle rp : rainbow) {
                batch.draw(
                        texture,
                        (int)(rp.getX() + xOffset),
                        (int)(rp.getY() + yOffset),
                        rp.getFadeLevel() * rp.getWidth(),
                        0,
                        rp.getWidth(),
                        rp.getHeight()
                );
            }

    }

    private int roundTo3(double n) {
        if (n > 0) {
            return 3*(int) (Math.ceil((n/3)));
        } else {
            return 3*(int) (Math.floor((n/3)));
        }

    }

}
