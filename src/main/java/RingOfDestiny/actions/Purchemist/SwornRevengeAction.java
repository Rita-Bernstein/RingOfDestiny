package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.powers.BleedingPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;


public class SwornRevengeAction  extends AbstractGameAction {
    private AbstractCreature target ;

    public SwornRevengeAction(AbstractCreature target) {
        this.actionType = ActionType.WAIT;
        this.target = target;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST && this.target.hasPower(BleedingPower.POWER_ID)) {
            int amount = this.target.getPower(ThornsPower.POWER_ID).amount;
            addToTop(new ApplyPowerAction(this.target, AbstractDungeon.player, new BleedingPower(this.target,AbstractDungeon.player, amount), amount));
        }

        tickDuration();
    }
}


