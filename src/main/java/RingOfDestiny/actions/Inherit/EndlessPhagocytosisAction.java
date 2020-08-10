package RingOfDestiny.actions.Inherit;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class EndlessPhagocytosisAction extends AbstractGameAction {
    private int increaseHpAmount;
    private DamageInfo info;
    private static final float DURATION = 0.1F;

    public EndlessPhagocytosisAction(AbstractCreature target, DamageInfo info, int maxHPAmount) {
        this.info = info;
        setValues(target, info);
        this.increaseHpAmount = maxHPAmount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = 0.1F;
    }


    public void update() {
        if (this.duration == 0.1F &&
                this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.NONE));
            this.target.damage(this.info);

            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead) {
                AbstractDungeon.player.increaseMaxHp(this.increaseHpAmount, false);


                if (this.target instanceof com.megacrit.cardcrawl.monsters.beyond.Donu) {
                    UnlockTracker.unlockAchievement("DONUT");
                }
            }

            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }


        tickDuration();
    }
}


