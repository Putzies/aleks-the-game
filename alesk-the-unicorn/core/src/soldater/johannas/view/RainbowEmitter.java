package soldater.johannas.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RainbowEmitter {

    private List<RainbowParticle> rainbow;

    public RainbowEmitter(){
        rainbow = new ArrayList<RainbowParticle>();
    }

    public List<RainbowParticle> getRainbow() {
        return rainbow;
    }

    public void update(double dTime, double x, double y, double yVel, double xVel, boolean add) {
        if (add && xVel > 0) {
            rainbow.add(new RainbowParticle(x, y));
            rainbow.add(new RainbowParticle(x+3, y+(yVel/3)));
            rainbow.add(new RainbowParticle(x+6, y+2*((yVel)/3)));
        }
        else if (add && xVel < 0) {
            rainbow.add(new RainbowParticle(x, y));
            rainbow.add(new RainbowParticle(x-3, y+(yVel/3)));
            rainbow.add(new RainbowParticle(x-6, y+2*((yVel)/3)));
        }

        for (Iterator<RainbowParticle> iterator = rainbow.iterator(); iterator.hasNext();) {
            RainbowParticle particle = iterator.next();
            particle.update(dTime);
            if (particle.timeToDie()) {
                iterator.remove();
            }
        }
    }

}
