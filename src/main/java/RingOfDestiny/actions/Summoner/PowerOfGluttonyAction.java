package RingOfDestiny.actions.Summoner;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.UUID;


public class PowerOfGluttonyAction extends AbstractGameAction {
    private DamageInfo info;
    private AbstractCard cardForReturn;
    private UUID uuid;
    private int increaseAmount;


    public PowerOfGluttonyAction(AbstractCreature target, DamageInfo info,  int incAmount,UUID targetUUID, AbstractCard cardForReturn) {
        this.info = info;
        setValues(target, info);
        this.uuid = targetUUID;
        this.increaseAmount = incAmount;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
        this.cardForReturn = cardForReturn;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER &&
                this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY));

            this.target.damage(this.info);

            if (((AbstractMonster) this.target).isDying || this.target.currentHealth <= 0) {

                         for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                               if (!c.uuid.equals(this.uuid))
                                     continue;
                               c.misc += this.increaseAmount;
                               c.applyPowers();
                               c.baseDamage = c.misc;
                               c.isDamageModified = false;
                             }
                         for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
                               c.misc += this.increaseAmount;
                               c.applyPowers();
                               c.baseDamage = c.misc;
                             }
                addToBot(new ExhaustToHandAction(cardForReturn));
            }

            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }


        tickDuration();
    }
}


