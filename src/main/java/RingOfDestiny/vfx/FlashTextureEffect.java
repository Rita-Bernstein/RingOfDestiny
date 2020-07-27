package RingOfDestiny.vfx;

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
    private float scaleFix ;

    public FlashTextureEffect(Texture texture,float x,float y,float scale) {
        this.x = x;
        this.y = y;
        this.scaleFix = scale;
        this.scale = Settings.scale;
        this.img = texture;
        this.duration = 0.7F;
        this.startingDuration = 0.7F;
        this.color = Color.WHITE.cpy();
        this.renderBehind = false;
    }


    public void update() {
        super.update();
        this.scale = Interpolation.exp5In.apply(Settings.scale, Settings.scale * 0.3F, this.duration / this.startingDuration) * this.scaleFix;
    }


    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        if (this.img != null) {
            sb.draw(this.img, this.x , this.y , this.img.getWidth()/ 2.0f, this.img.getWidth()/ 2.0f, this.img.getWidth(), this.img.getWidth(), this.scale * 12.0F, this.scale * 12.0F, 0.0F, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
            sb.draw(this.img, this.x , this.y , this.img.getWidth()/ 2.0f, this.img.getWidth()/ 2.0f, this.img.getWidth(), this.img.getWidth(), this.scale * 10.0F, this.scale * 10.0F, 0.0F, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
            sb.draw(this.img, this.x , this.y , this.img.getWidth()/ 2.0f, this.img.getWidth()/ 2.0f, this.img.getWidth(), this.img.getWidth(), this.scale * 8.0F, this.scale * 8.0F, 0.0F, 0, 0, this.img.getWidth(), this.img.getHeight(),  false, false);
            sb.draw(this.img, this.x , this.y , this.img.getWidth()/ 2.0f, this.img.getWidth()/ 2.0f, this.img.getWidth(), this.img.getWidth(), this.scale * 7.0F, this.scale * 7.0F, 0.0F, 0, 0, this.img.getWidth(), this.img.getHeight(),  false, false);
        }
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}


