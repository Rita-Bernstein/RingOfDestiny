package RingOfDestiny.actions.MagicBullet;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class DeadlyShootAction extends AbstractGameAction {
    private int energyGainAmt;
    private DamageInfo info;
    private AbstractCard cardForReturn;

    public DeadlyShootAction(AbstractCreature target, DamageInfo info, int energyAmt, AbstractCard cardForReturn) {
        this.info = info;
        setValues(target, info);
        this.energyGainAmt = energyAmt;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.cardForReturn = cardForReturn;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER && this.target != null) {

            this.target.damage(this.info);

            if (((AbstractMonster) this.target).isDying || this.target.currentHealth <= 0) {
                addToBot(new AttackDamageRandomEnemyAction(this.cardForReturn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }

            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }


        tickDuration();
    }
}


