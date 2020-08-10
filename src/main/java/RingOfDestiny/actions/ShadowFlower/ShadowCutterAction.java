package RingOfDestiny.actions.ShadowFlower;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class ShadowCutterAction
        extends AbstractGameAction {
    private int energyGainAmt;
    private DamageInfo info;
    private AbstractCard cardForReturn;

    public ShadowCutterAction(AbstractCreature target, DamageInfo info, int drawCardAmt) {
        this.info = info;
        setValues(target, info);
        this.energyGainAmt = drawCardAmt;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER &&
                this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));

            this.target.damage(this.info);

            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead) {
                addToBot(new DrawCardAction(this.energyGainAmt));
            }

            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }


        tickDuration();
    }
}


