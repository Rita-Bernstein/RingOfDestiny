package RingOfDestiny.vfx;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

public class LayerAnimation {
    TextureAtlas atlas;
    ArrayList<Frame> frames;
    Frame currFrame;
    int currFrameIndex = 0;
    int currDelay = 0;
    String spriteSheetId;
    float rootPositionX = 0.0f;
    float rootPositionY = 0.0f;
    float xPosition = 0.0f;
    float yPosition = 0.0f;

    boolean flipX = false;
    boolean flipY = false;

    boolean loop = false;
    boolean isDone = false;

    void update() {
        if (currFrameIndex >= frames.size()) {
            return;
        }


        currFrame = frames.get(currFrameIndex).makeCopy();

        if (currDelay >= currFrame.delay) {
            currFrameIndex++;
            currDelay = 0;
        }

        if (currFrameIndex >= frames.size()) {
            if (!loop)
                isDone = true;
            else
                currFrameIndex = 0;
        }
    }

    void render(SpriteBatch sb) {
        if (currFrameIndex >= frames.size()) {
            return;
        }
        if (currFrame != null) {
            sb.setColor(currFrame.tint);

            TextureAtlas.AtlasRegion img = atlas.findRegion(spriteSheetId + String.format("-%05d", currFrameIndex));

//            if (img.rotate) {
//                sb.draw(img.getTexture(),
//                        rootPositionX + xPosition + img.offsetX - currFrame.originalX,
//
//                        rootPositionY + yPosition + img.offsetY + img.packedHeight  + currFrame.originalY,
//                        0.0f,
//                        0.0f,
//                        img.packedWidth,
//                        img.packedHeight,
//                        currFrame.xScale, currFrame.yScale,
//                        -90,
//                        img.getRegionX(), img.getRegionY(),
//                        img.getRegionWidth(), img.getRegionHeight(),
//                        flipX, flipY
//                );
//            } else {
                sb.draw(img.getTexture(),
                        rootPositionX + xPosition + img.offsetX *currFrame.xScale - currFrame.originalX,
                        rootPositionY + yPosition + img.offsetY *currFrame.yScale + currFrame.originalY,
                        0.0f,
                        0.0f,
                        img.packedWidth,
                        img.packedHeight,
                        currFrame.xScale, currFrame.yScale,
                        0,
                        img.getRegionX(), img.getRegionY(),
                        img.getRegionWidth(), img.getRegionHeight(),
                        flipX, flipY
                );
//            }


        }
    }
}
