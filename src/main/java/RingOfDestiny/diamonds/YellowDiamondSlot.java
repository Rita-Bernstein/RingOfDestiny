package RingOfDestiny.diamonds;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class YellowDiamondSlot extends AbstractDiamond {

    public YellowDiamondSlot(float x, float y, float scale, float angle,int index) {
        this.current_x = x;
        this.current_y = y;
        this.angle = angle;
        this.scale = scale;
        this.index = index;
        this.particleColor = new Color(0.953f,0.871f,0.531f,0.0f);
    }
}


