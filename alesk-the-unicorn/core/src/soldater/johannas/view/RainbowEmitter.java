package soldater.johannas.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RainbowEmitter {

    private List<RainbowParticle> rainbow;
    private Texture texture = new Texture("rainbow2.png");
    private int width = 3;
    private int roundedTipSize;
    private double x;
    private double y;
    private double xVel;
    private double yVel;
    private Random rand = new Random();

    public RainbowEmitter(){
        rainbow = new ArrayList<RainbowParticle>();
        roundedTipSize = texture.getWidth()/width;
    }

    // Add new rainbow particles after the unicorn and delete them after a while
    public void update(double dTime, double x, double y, boolean add) {

        // Make the rainbow start at the middle of the unicorn
        y += 15;

        // Since the width of the rainbow particle sprite is 3, xVel has to be rounded to a multiple of three, to make it look nice
        xVel = roundTo3(x - this.x);

        yVel = (y - this.y);
        this.x = x;
        this.y = y;
        if (add) {
            addParticles(x, y);
        }
        deleteParticles(dTime);
    }

    // Add particles at the current location and in the gap between current and last position
    private void addParticles(double x, double y) {
            int ratio = (int)Math.abs(xVel)/width;
            for(double i = width; i <= xVel; i += width) {
                rainbow.add(new RainbowParticle(x-(xVel-i), y - ((xVel-i)/width)*(yVel)/ratio));
            }
            for(double i = width; i <= -xVel; i += width) {
                rainbow.add(new RainbowParticle(x+(-xVel-i), y - ((-xVel-i)/width)*(yVel)/ratio));
            }
    }

    // Remove particles when it is time for them to be deleted
    private void deleteParticles(double dTime) {
        for (Iterator<RainbowParticle> iterator = rainbow.iterator(); iterator.hasNext();) {
            RainbowParticle particle = iterator.next();
            particle.update(dTime);
            if (particle.timeToDie()) {
                iterator.remove();
            }
        }
    }

    // Draw the rainbow
    public void draw(SpriteBatch batch, int xOffset, int yOffset) {
            for (int i = 0; i< rainbow.size(); i++) {
                RainbowParticle rp = rainbow.get(i);

                // Draw a rounded tip at the beginning of the rainbow
                if(rainbow.size() - i < roundedTipSize) {
                    batch.draw(
                            texture,
                            (int)(rp.getX() + xOffset),
                            (int)(rp.getY() + yOffset),
                            Math.abs((rainbow.size() - i)-roundedTipSize)*rp.getWidth(),
                            rp.getFadeLevel() * rp.getHeight(),
                            rp.getWidth(),
                            rp.getHeight()
                    );
                // Draw the rest of the rainbow
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
