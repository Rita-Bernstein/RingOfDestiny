package RingOfDestiny.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;



public class AbstractRingEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float scale;
    private float timer;
    private Texture[] img;
    private int delay;
    private int frame;
    private int frameNum = 0;
    private int delayTimer = 0;

    public AbstractRingEffect(String id, float x, float y, float scale, int frame, int delay) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.frame = frame;
        this.delay = delay;
        this.img = new Texture[this.frame];

        for (int i = 0; i < this.frame; i++) {
            img[i] = ImageMaster.loadImage(String.format("RingOfDestiny/img/vfx/" + id + "_unpack" + "/" + id + "-" + "%05d.png", i));
        }

    }

    public void update() {
        if (this.delayTimer < this.delay) {
            delayTimer++;
        } else {
            delayTimer = 0;
            if (this.frameNum < this.frame - 1) {
                frameNum++;
            } else {
                this.isDone = true;
            }
        }
    }


    public void render(SpriteBatch sb) {


        sb.setColor(Color.WHITE);
        if (this.img.length != 0)
            sb.draw(this.img[frameNum],
                    this.x - this.img[frameNum].getWidth() / 2.0f,
                    this.y - this.img[frameNum].getHeight() / 2.0f,
                    this.img[frameNum].getWidth() / 2.0f,
                    this.img[frameNum].getHeight() / 2.0f,
                    this.img[frameNum].getWidth(),
                    this.img[frameNum].getHeight(),
                    Settings.scale / this.scale,
                    Settings.scale / this.scale,
                    0.0f, 0, 0,
                    this.img[frameNum].getWidth(),
                    this.img[frameNum].getHeight(),
                    false, false);
    }

    public void dispose() {
    }
}