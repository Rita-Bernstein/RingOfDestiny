package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.powers.BleedingPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;


public class BleedingLoseHpAction extends AbstractGameAction {
    private static final float DURATION = 0.33F;

    public BleedingLoseHpAction(AbstractCreature target, AbstractCreature source, int amount, AbstractGameAction.AttackEffect effect) {
        setValues(target, source, amount);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.attackEffect = effect;
        this.duration = 0.33F;
    }


    public void update() {
        if ((AbstractDungeon.getCurrRoom()).phase != AbstractRoom.RoomPhase.COMBAT) {
            this.isDone = true;

            return;
        }
        if (this.duration == 0.33F && this.target.currentHealth > 0) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
        }


        tickDuration();

        if (this.isDone) {
            if (this.target.currentHealth > 0) {
                this.target.tint.color = Color.CHARTREUSE.cpy();
                this.target.tint.changeColor(Color.WHITE.cpy());
                this.target.damage(new DamageInfo(this.source, this.amount, DamageInfo.DamageType.HP_LOSS));

            }

            AbstractPower p = this.target.getPower(BleedingPower.POWER_ID);
            if (p != null) {
                this.target.powers.remove(p);
            }

            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
            addToTop(new WaitAction(0.1F));
        }
    }
}


