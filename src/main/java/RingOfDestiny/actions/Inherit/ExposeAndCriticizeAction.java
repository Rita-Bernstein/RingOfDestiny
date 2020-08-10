package RingOfDestiny.actions.Inherit;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class ExposeAndCriticizeAction extends AbstractGameAction {

    private DamageInfo info;
    private AbstractCard cardForReturn;
    private int amount;
    private boolean isDark;

    public ExposeAndCriticizeAction(AbstractCreature target, DamageInfo info, int amount, boolean isDark) {
        this.info = info;
        setValues(target, info);
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.isDark = isDark;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER &&
                this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));

            this.target.damage(this.info);

            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead)  {
                if (this.isDark) {
                    addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            new MetallicizePower(AbstractDungeon.player, this.amount), this.amount));

                } else {
                    addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                            new StrengthPower(AbstractDungeon.player, this.amount), this.amount));

                }

            }

            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }


        tickDuration();
    }
}


