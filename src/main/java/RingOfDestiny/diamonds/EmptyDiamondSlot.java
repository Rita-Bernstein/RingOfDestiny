package RingOfDestiny.diamonds;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;

public class EmptyDiamondSlot extends AbstractDiamond {
    public static final String ORB_ID = RingOfDestiny.makeID("EmptyDiamond");


    public EmptyDiamondSlot(float x, float y) {
        this.angle = MathUtils.random(360.0F);
        this.ID = ORB_ID;
        this.evokeAmount = 0;
        this.cX = x;
        this.cY = y;
        this.channelAnimTimer = 0.5F;
    }

    public EmptyDiamondSlot() {
        this.angle = MathUtils.random(360.0F);
        this.evokeAmount = 0;
        this.cX = AbstractDungeon.player.drawX + AbstractDungeon.player.hb_x;
        this.cY = AbstractDungeon.player.drawY + AbstractDungeon.player.hb_y + AbstractDungeon.player.hb_h / 2.0F;
    }


    public void onEvoke() {
    }

    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 10.0F;
    }


    public void render(SpriteBatch sb) {
        sb.setColor(this.c);

        sb.draw(ImageMaster.ORB_SLOT_2, this.cX - 48.0F - this.bobEffect.y / 8.0F, this.cY - 48.0F + this.bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);

        sb.draw(ImageMaster.ORB_SLOT_1, this.cX - 48.0F + this.bobEffect.y / 8.0F, this.cY - 48.0F - this.bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);

        renderText(sb);
        this.hb.render(sb);
    }

    public AbstractDiamond makeCopy() {
        return new EmptyDiamondSlot();
    }

    public void playChannelSFX() {
    }
}


