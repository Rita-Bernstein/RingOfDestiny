package RingOfDestiny.actions.Inherit;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.red.Metallicize;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;


public class DoubleMetallicizeAction  extends AbstractGameAction {
    private AbstractPlayer p ;

    public DoubleMetallicizeAction() {
        this.actionType = ActionType.WAIT;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST && this.p.hasPower(ThornsPower.POWER_ID)) {
            int strAmt = this.p.getPower(MetallicizePower.POWER_ID).amount;
            addToTop(new ApplyPowerAction(this.p, this.p, new MetallicizePower(this.p, strAmt), strAmt));
        }

        tickDuration();
    }
}


