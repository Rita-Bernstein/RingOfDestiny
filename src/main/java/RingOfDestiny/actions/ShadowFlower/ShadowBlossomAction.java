package RingOfDestiny.actions.ShadowFlower;

import RingOfDestiny.cards.ShadowFlower.ShadowRose;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class ShadowBlossomAction
        extends AbstractGameAction {
    private int cardAmt;
    private DamageInfo info;
    private boolean upgraded;

    public ShadowBlossomAction(AbstractCreature target, DamageInfo info, int cardAmt,boolean upgraded) {
        this.info = info;
        setValues(target, info);
        this.cardAmt = cardAmt;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.upgraded = upgraded;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER && this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.NONE));

            this.target.damage(this.info);

            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead)  {
                AbstractCard s = (new ShadowRose()).makeCopy();
                if(upgraded) s.upgrade();
                addToBot(new MakeTempCardInHandAction(s, this.cardAmt));
            }

            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }


        tickDuration();
    }
}


