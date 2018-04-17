package soldater.johannas.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RainbowEmitter {

    private List<RainbowParticle> rainbow;
    private Texture texture = new Texture("rainbow.png");
    private int width = 3;
    private int nrXSprites;
    private double x;
    private double y;
    private double xVel;
    private double yVel;
    private Random rand = new Random();

    public RainbowEmitter(){
        rainbow = new ArrayList<RainbowParticle>();
        nrXSprites = texture.getWidth()/width;

    }

    public void update(double dTime, double x, double y, boolean add) {
        y += 15;
        xVel = roundTo3(x - this.x);
        yVel = (y - this.y);
        this.x = x;
        this.y = y;
        if (add) {
            addParticles(x, y);
        }
        deleteParticles(dTime);
    }

    private void addParticles(double x, double y) {
            int ratio = (int)Math.abs(xVel)/width;
            for(double i = width; i <= xVel; i += width) {
                rainbow.add(new RainbowParticle(x-(xVel-i), y - ((xVel-i)/width)*(yVel)/ratio));
            }
            for(double i = width; i <= -xVel; i += width) {
                rainbow.add(new RainbowParticle(x+(-xVel-i), y - ((-xVel-i)/width)*(yVel)/ratio));
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
            for (int i = 0; i< rainbow.size(); i++) {
                RainbowParticle rp = rainbow.get(i);
                if(rainbow.size() - i < nrXSprites) {
                    batch.draw(
                            texture,
                            (int)(rp.getX() + xOffset),
                            (int)(rp.getY() + yOffset),
                            Math.abs((rainbow.size() - i)-nrXSprites)*rp.getWidth(),
                            0,
                            rp.getWidth(),
                            rp.getHeight()
                    );
                } else {
                    batch.draw(
                            texture,
                            (int)(rp.getX() + xOffset),
                            (int)(rp.getY() + yOffset),
                            0,
                            rp.getFadeLevel() * rp.getHeight(),
                            rp.getWidth(),
                            rp.getHeight()
                    );
                }
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
