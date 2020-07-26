package RingOfDestiny.diamonds;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BlueDiamondSlot extends AbstractDiamond {

    public BlueDiamondSlot(float x, float y, float scale, float angle) {
        this.current_x = x;
        this.current_y = y;
        this.angle = angle;
        this.scale = scale;
        this.channelAnimTimer = 0.5F;
        this.particleColor = new Color(0.386f,0.828f,0.949f,1.0f);
    }
}


