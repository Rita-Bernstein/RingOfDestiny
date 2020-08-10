package RingOfDestiny.actions.Inherit;

import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.red.Metallicize;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static RingOfDestiny.powers.Inherit.ConvictionPower.*;


public class DoubleConvictionAction  extends AbstractGameAction {
    private AbstractPlayer p ;

    public DoubleConvictionAction(AbstractMonster m) {
        this.target = m;
        this.actionType = ActionType.WAIT;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST && this.target.hasPower(ConvictionPower.POWER_ID)) {
            int strAmt = this.target.getPower(ConvictionPower.POWER_ID).amount;
            addToTop(new ApplyPowerAction(this.target,this.target, new ConvictionPower(this.p, strAmt), strAmt));
        }

        tickDuration();
    }
}


