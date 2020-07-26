package RingOfDestiny.diamonds;

import RingOfDestiny.vfx.DiamondFireEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;


public abstract class AbstractDiamond {
    public float cX = 0.0F;
    public float cY = 0.0F;
    protected float current_x = 0.0f;
    protected float current_y = 0.0f;
    protected Color c;
    protected Color particleColor;
    protected float angle;
    protected float scale;
    protected boolean isSocket;
    protected float channelAnimTimer;
    protected float particleTimer;


    protected Texture slot = ImageMaster.loadImage("RingOfDestiny/img/diamonds/lightbg.png");
    protected Texture slot2 = ImageMaster.loadImage("RingOfDestiny/img/diamonds/light.png");

    public AbstractDiamond() {
        this.c = Settings.CREAM_COLOR.cpy();
        this.isSocket = false;
        this.channelAnimTimer = 0.5F;
        this.particleTimer = 0.06f;
    }

    public void onEvoke() {
        this.isSocket = false;
    }

    public void onSocket() {
        this.isSocket = true;
    }

    public void update() {
        if (isSocket) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.06F;
                AbstractDungeon.effectList.add(new DiamondFireEffect(this.cX + this.current_x,this.cY + this.current_y,this.scale,this.particleColor));
            }
        }


    }


    public void updateAnimation() {
        if (this.channelAnimTimer != 0.0F) {
            this.channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (this.channelAnimTimer < 0.0F) {
                this.channelAnimTimer = 0.0F;
            }
        }

        this.c.a = Interpolation.pow2In.apply(1.0F, 0.01F, this.channelAnimTimer / 0.5F);
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.c);

        sb.draw(slot,
                this.cX + this.current_x,
                this.cY + this.current_y,
                27.0F, 27.0F, 54.0F, 54.0F, this.scale, this.scale, this.angle, 0, 0, 54, 54, false, false);

        if (this.isSocket) {
            sb.draw(slot2,
                    this.cX + this.current_x,
                    this.cY + this.current_y,
                    27.0F, 27.0F, 54.0F, 54.0F, this.scale, this.scale, this.angle, 0, 0, 54, 54, false, false);
        }
    }
}


