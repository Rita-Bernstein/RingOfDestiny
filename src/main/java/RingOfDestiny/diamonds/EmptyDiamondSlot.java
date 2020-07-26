package RingOfDestiny.diamonds;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EmptyDiamondSlot extends AbstractDiamond {
    public static final String ORB_ID = RingOfDestiny.makeID("EmptyDiamond");
    private float current_x = 0.0f;
    private float current_y = 0.0f;

    public EmptyDiamondSlot(float x, float y, float scale, float angle) {
        this.ID = ORB_ID;
        this.current_x = x;
        this.current_y = y;
        this.angle = angle;
        this.scale = scale;
        this.channelAnimTimer = 0.5F;
    }

    public void onEvoke() {
    }

    public void updateAnimation() {
        super.updateAnimation();
    }


    public void render(SpriteBatch sb) {
        sb.setColor(this.c);

        sb.draw(slot,
                this.cX + this.current_x,
                this.cY + this.current_y,
                27.0F, 27.0F, 54.0F, 54.0F, this.scale, this.scale, this.angle, 0, 0, 54, 54, false, false);

        sb.draw(slot2,
                this.cX + this.current_x,
                this.cY + this.current_y,
                27.0F, 27.0F, 54.0F, 54.0F, this.scale, this.scale, this.angle, 0, 0, 54, 54, false, false);
    }
}


