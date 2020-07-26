package RingOfDestiny.diamonds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;


public abstract class AbstractDiamond {
    public String description;
    public String ID;

    public float cX = 0.0F;
    public float cY = 0.0F;
    protected Color c;
    protected float angle;
    protected float scale;
    protected boolean isSocket;
    protected float channelAnimTimer;


    protected Texture slot = ImageMaster.loadImage("RingOfDestiny/img/diamonds/lightbg.png");
    protected Texture slot2 = ImageMaster.loadImage("RingOfDestiny/img/diamonds/light.png");

    public AbstractDiamond() {
        this.c = Settings.CREAM_COLOR.cpy();
        this.isSocket = false;
        this.channelAnimTimer = 0.5F;
    }

    public abstract void onEvoke();

    public void onSocket() {
    }

    public void update() {

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


    public abstract void render(SpriteBatch paramSpriteBatch);


}


