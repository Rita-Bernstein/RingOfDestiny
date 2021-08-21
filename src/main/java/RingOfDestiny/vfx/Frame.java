package RingOfDestiny.vfx;

import com.badlogic.gdx.graphics.Color;

public class Frame {
    float originalX = 0.0f;
    float originalY = 0.0f;
    float xScale;
    float yScale;
    int delay;
    boolean visible;
    Color tint;
    int rotation;

    public Frame makeCopy() {
        Frame ret = new Frame();
        ret.originalX = this.originalX;
        ret.originalY = this.originalY;
        ret.xScale = this.xScale;
        ret.yScale = this.yScale;
        ret.delay = this.delay;
        ret.visible = this.visible;
        ret.tint = this.tint.cpy();
        ret.rotation = this.rotation;
        return ret;
    }
}
