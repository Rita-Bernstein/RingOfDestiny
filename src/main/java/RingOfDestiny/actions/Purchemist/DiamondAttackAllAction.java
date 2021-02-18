package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.AbstractRingPower;
import RingOfDestiny.vfx.FlashTextureEffect;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DiamondAttackAllAction extends AbstractGameAction {
    private int evokeAmount;
    private float imgScale,tX,tY,imgFix_X,imgFix_Y;
    private Texture aoeImg = ImageMaster.loadImage("RingOfDestiny/img/diamonds/intent/aoe.png");

    public DiamondAttackAllAction(int evokeAmount,float imgScale,float tx,float ty,float imgFix_X,float imgFix_Y ) {
        this.evokeAmount = evokeAmount;
        this.imgScale = imgScale;
        this.tX = tx;
        this.tY = ty;
        this.imgFix_X = imgFix_X;
        this.imgFix_Y = imgFix_Y;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null,
                    DamageInfo.createDamageMatrix(this.evokeAmount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));

            AbstractDungeon.topLevelEffectsQueue.add(new FlashTextureEffect(this.aoeImg,
                    this.tX + imgFix_X * Settings.scale,
                    this.tY + imgFix_Y * Settings.scale,
                    imgScale));
            isDone = true;
            return;
        }
        tickDuration();
    }
}


