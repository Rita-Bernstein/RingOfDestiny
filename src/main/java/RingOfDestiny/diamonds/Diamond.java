package RingOfDestiny.diamonds;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Diamond extends AbstractDiamond {
    public static final String ORB_ID = RingOfDestiny.makeID("Diamond");
    private boolean hFlip1;
    private boolean hFlip2;

    public Diamond() {
        this.vfxTimer = 1.0F;
        this.vfxIntervalMin = 0.15F;
        this.vfxIntervalMax = 0.8F;


        this.hFlip1 = MathUtils.randomBoolean();
        this.hFlip2 = MathUtils.randomBoolean();

        this.ID = ORB_ID;
        this.channelAnimTimer = 0.5F;
    }

    private float vfxTimer;
    private float vfxIntervalMin;
    private float vfxIntervalMax;


    public void onEvoke() {
    }


    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 180.0F;

        this.vfxTimer -= Gdx.graphics.getDeltaTime();
        if (this.vfxTimer < 0.0F) {
//            AbstractDungeon.effectList.add(new DiamondOrbPassiveEffect(this.cX, this.cY));
            if (MathUtils.randomBoolean()) {
//                AbstractDungeon.effectList.add(new DiamondOrbPassiveEffect(this.cX, this.cY));
            }
            this.vfxTimer = MathUtils.random(this.vfxIntervalMin, this.vfxIntervalMax);
        }
    }


    public void onEndOfTurn() {
        float speedTime = 0.6F / AbstractDungeon.player.orbs.size();
        if (Settings.FAST_MODE) {
            speedTime = 0.0F;
        }

//        AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.Diamond), speedTime));


    }


    public void triggerEvokeAnimation() {
        CardCrawlGame.sound.play("ORB_Diamond_EVOKE", 0.1F);
//        AbstractDungeon.effectsQueue.add(new DiamondOrbActivateEffect(this.cX, this.cY));
    }


    public void render(SpriteBatch sb) {
        sb.setColor(this.c);
        /*
        sb.draw(ImageMaster.Diamond_ORB_RIGHT, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F + this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, this.hFlip1, false);
        sb.draw(ImageMaster.Diamond_ORB_LEFT, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F - this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, this.hFlip1, false);
        sb.draw(ImageMaster.Diamond_ORB_MIDDLE, this.cX - 48.0F - this.bobEffect.y / 4.0F, this.cY - 48.0F + this.bobEffect.y / 2.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, this.hFlip2, false);
*/
        this.hb.render(sb);
    }


    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_Diamond_CHANNEL", 0.1F);
    }


    public AbstractDiamond makeCopy() {
        return new Diamond();
    }
}


