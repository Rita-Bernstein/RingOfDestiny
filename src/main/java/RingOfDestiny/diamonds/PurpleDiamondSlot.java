package RingOfDestiny.diamonds;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PurpleDiamondSlot extends AbstractDiamond {

    public PurpleDiamondSlot(float x, float y, float scale, float angle) {
        this.current_x = x;
        this.current_y = y;
        this.angle = angle;
        this.scale = scale;
        this.channelAnimTimer = 0.5F;
        this.particleColor = new Color(0.859f,0.382f,0.925f,1.0f);
    }
}


