package RingOfDestiny.powers.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.powers.AbstractRingPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.MetallicizePower;


public class LoseMetallicizePower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("LoseMetallicizePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LoseMetallicizePower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        updateDescription();
        loadRingRegion("4028");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        if (this.owner.hasPower(MetallicizePower.POWER_ID)) {
            addToBot(new ReducePowerAction(this.owner, this.owner, MetallicizePower.POWER_ID, this.amount));
        }
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, LoseMetallicizePower.POWER_ID));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}


