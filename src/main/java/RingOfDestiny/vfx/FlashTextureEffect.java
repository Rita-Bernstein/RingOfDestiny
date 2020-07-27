package RingOfDestiny.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class FlashTextureEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private Texture img;
    private static final int W = 32;
    private float scale;
    private float scaleFix;

    public FlashTextureEffect(Texture texture, float x, float y, float scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.img = texture;
        this.duration = 2.0F;
        this.startingDuration = 0.7F;
        this.color = Color.WHITE.cpy();
        this.renderBehind = false;
    }


    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }


    public void render(SpriteBatch sb) {
        float tmp = Interpolation.exp10In.apply(0.0F, 4.0F, this.duration / 2.0F) * Settings.scale;
        sb.setBlendFunction(770, 1);
        this.color.a = this.duration * 0.2F;
        sb.setColor(this.color);

        if (this.img != null) {
            sb.draw(this.img, this.x - this.img.getWidth() / 2.0f , this.y  - this.img.getWidth() / 2.0f, this.img.getWidth() / 2.0f, this.img.getWidth() / 2.0f, this.img.getWidth(), this.img.getWidth(), this.scale + tmp, this.scale + tmp, 0.0F, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
            sb.draw(this.img, this.x - this.img.getWidth() / 2.0f  , this.y  - this.img.getWidth() / 2.0f, this.img.getWidth()/ 2.0f, this.img.getWidth()/ 2.0f, this.img.getWidth(), this.img.getWidth(), this.scale + tmp * 0.66F, this.scale + tmp * 0.66F,  0.0F, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
            sb.draw(this.img, this.x  - this.img.getWidth() / 2.0f , this.y - this.img.getWidth() / 2.0f , this.img.getWidth()/ 2.0f, this.img.getWidth()/ 2.0f, this.img.getWidth(), this.img.getWidth(), this.scale + tmp / 3.0F, this.scale + tmp / 3.0F,0.0F, 0, 0, this.img.getWidth(), this.img.getHeight(),  false, false);

        }
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}


