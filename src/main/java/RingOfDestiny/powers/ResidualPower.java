package RingOfDestiny.powers;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.ShadowFlower.ShadowRose;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ResidualPower extends AbstractPower {
    public static final String POWER_ID = RingOfDestiny.makeID("ResidualPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static final int limite = 3;

    public ResidualPower(AbstractCreature owner, int amt) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amt;
        if (this.amount > limite) this.amount = limite;
        updateDescription();
        loadRegion("infiniteBlades");
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        if (this.amount > limite) this.amount = limite;
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        if (this.amount + stackAmount <= limite) {
            this.amount += stackAmount;
        } else {
            this.amount = limite;
        }
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + limite + DESCRIPTIONS[2];
    }
}


