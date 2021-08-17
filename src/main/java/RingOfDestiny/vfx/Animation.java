package RingOfDestiny.vfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Animation {
    ArrayList<LayerAnimation> layerAnimations;
    boolean isDone = false;
    float xPosition = 0;
    float yPosition = 0;

    void update() {
        if (!isDone) {
            for (LayerAnimation layerAnimation : layerAnimations) {
                layerAnimation.rootPositionX = xPosition;
                layerAnimation.rootPositionY = yPosition;
                layerAnimation.update();

                if (layerAnimation.isDone) {
                    this.isDone = true;
                }
            }
        }
    }

    void render(SpriteBatch sb) {
        for (LayerAnimation layerAnimation : layerAnimations) {
            layerAnimation.render(sb);
        }
    }
}