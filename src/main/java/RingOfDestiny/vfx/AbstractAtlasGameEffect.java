package RingOfDestiny.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.ArrayList;

public class AbstractAtlasGameEffect extends AbstractGameEffect {
    private Info info;
    public TextureAtlas atlas;
    private Animation curAnimation;
    public float scale = 1.0F;

    private float frameTimer = 0.0F;
    public float xPosition;
    public float yPosition;
    public boolean flipX = false;
    public boolean flipY = false;

    public AbstractAtlasGameEffect(String id, float x, float y, float scale, int delay, boolean loop) {
        this.atlas = new TextureAtlas(Gdx.files.internal("RingOfDestiny/img/vfx/Atlas/" + id + "_unpack.atlas"));
        this.xPosition = x;
        this.yPosition = y;
        this.info = new Info();
        this.info.fps = Settings.MAX_FPS;
        this.info.id = id;

        Animation animation = new Animation();
        animation.xPosition = x;
        animation.yPosition = y;

        ArrayList<LayerAnimation> layerAnimationList = new ArrayList<>();

        LayerAnimation layerAnimation = new LayerAnimation();

        layerAnimation.spriteSheetId = id;
        layerAnimation.loop = loop;
        System.out.println(atlas.getRegions().size);
        ArrayList<Frame> frames = new ArrayList<>();
        for (int i = 0; i < atlas.getRegions().size; i++) {
            Frame frame = new Frame();
            frame.tint = Color.WHITE.cpy();
            frame.rotation = 0;
            frame.xScale = scale;
            frame.yScale = scale;
            frame.delay = delay;
            frames.add(frame);
        }

        layerAnimation.frames = frames;
        layerAnimationList.add(layerAnimation);

        animation.layerAnimations = layerAnimationList;
        this.curAnimation = animation;
    }

    public AbstractAtlasGameEffect(String id, float x, float y) {
        this(id, x, y, 1.0f, 3, false);
    }


    public AbstractAtlasGameEffect(String id, float x, float y, boolean loop) {
        this(id, x, y, 1.0f, 3, loop);
    }

    public AbstractAtlasGameEffect(String id, float x, float y, float scale) {
        this(id, x, y, scale, 3, false);
    }

    public AbstractAtlasGameEffect(String id, float x, float y, float scale, boolean loop) {
        this(id, x, y, scale, 3, loop);
    }

    public void update() {
        frameTimer += Gdx.graphics.getDeltaTime();

        curAnimation.update();

        if (frameTimer >= 1.0F / (float) this.info.fps) {
            if (curAnimation != null) {
                for (LayerAnimation layerAnimation : curAnimation.layerAnimations) {
                    layerAnimation.currDelay++;
                }
            }
            frameTimer = 0;
        }

        if (isCurAnimationDone())
            this.isDone = true;
    }

    public void render(SpriteBatch sb) {
        if (curAnimation != null) curAnimation.render(sb);
    }


    @Override
    public void dispose() {
        atlas.dispose();
    }

    public boolean isCurAnimationDone() {
        if (curAnimation != null) {
            return curAnimation.isDone;
        }
        return true;
    }

    public boolean isCurAnimation(Animation animation) {
        return curAnimation == animation;
    }

    public static class Info {
        int fps;
        String id;
    }


    public class Animation {
        ArrayList<LayerAnimation> layerAnimations;
        boolean isDone = false;
        float xPosition = 0;
        float yPosition = 0;

        void update() {
            if (!isDone) {
                for (LayerAnimation layerAnimation : layerAnimations) {
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

    private class LayerAnimation {
        ArrayList<Frame> frames;
        Frame currFrame;
        int currFrameIndex = 0;
        int currDelay = 0;
        String spriteSheetId;
        float xPosition = 0;
        float yPosition = 0;
        boolean loop = false;
        boolean isDone = false;

        void update() {
            if (currFrameIndex >= frames.size() && !loop) {
                isDone = true;
                return;
            }


            currFrame = frames.get(currFrameIndex).makeCopy();

            if (currDelay >= currFrame.delay) {
                currFrameIndex++;
                currDelay = 0;
            }

            if (currFrameIndex >= frames.size()) {
                currFrameIndex = 0;
            }
        }

        void render(SpriteBatch sb) {
            if (currFrameIndex >= frames.size()) {
                return;
            }
            if (currFrame != null) {
                sb.setColor(currFrame.tint);

                AtlasRegion img = AbstractAtlasGameEffect.this.atlas.findRegion(spriteSheetId + String.format("-%05d", currFrameIndex));

                if (img.rotate) {
                    sb.draw(img.getTexture(),
                            AbstractAtlasGameEffect.this.curAnimation.xPosition + AbstractAtlasGameEffect.this.curAnimation.layerAnimations.get(0).xPosition
                                    + img.offsetX * Settings.scale - currFrame.originalX,

                            AbstractAtlasGameEffect.this.curAnimation.yPosition + AbstractAtlasGameEffect.this.curAnimation.layerAnimations.get(0).yPosition
                                    + img.offsetY * Settings.scale + img.packedWidth * Settings.scale + currFrame.originalY,
                            0.0f,
                            0.0f,
                            img.packedWidth,
                            img.packedHeight,
                            currFrame.xScale, currFrame.yScale,
                            -90,
                            img.getRegionX(), img.getRegionY(),
                            img.getRegionWidth(), img.getRegionHeight(),
                            AbstractAtlasGameEffect.this.flipX, AbstractAtlasGameEffect.this.flipY
                    );
                } else {
                    sb.draw(img.getTexture(),
                            AbstractAtlasGameEffect.this.curAnimation.xPosition + AbstractAtlasGameEffect.this.curAnimation.layerAnimations.get(0).xPosition
                                    + img.offsetX * Settings.scale + currFrame.originalX,

                            AbstractAtlasGameEffect.this.curAnimation.yPosition + AbstractAtlasGameEffect.this.curAnimation.layerAnimations.get(0).yPosition
                                    + img.offsetY * Settings.scale - currFrame.originalY,
                            0.0f,
                            0.0f,
                            img.packedWidth,
                            img.packedHeight,
                            currFrame.xScale, currFrame.yScale,
                            0,
                            img.getRegionX(), img.getRegionY(),
                            img.getRegionWidth(), img.getRegionHeight(),
                            AbstractAtlasGameEffect.this.flipX, AbstractAtlasGameEffect.this.flipY
                    );
                }


            }
        }
    }

    private static class Frame {
        float originalX = 0.0f;
        float originalY = 0.0f;
        float xScale;
        float yScale;
        int delay;
        boolean visible;
        Color tint;
        int rotation;

        Frame makeCopy() {
            Frame ret = new Frame();
            ret.xScale = this.xScale;
            ret.yScale = this.yScale;
            ret.delay = this.delay;
            ret.visible = this.visible;
            ret.tint = this.tint.cpy();
            ret.rotation = this.rotation;
            return ret;
        }
    }
}
