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

public class BeltPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("BeltPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BeltPower(AbstractCreature owner, int bladeAmt) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = bladeAmt;
        updateDescription();
        loadRingRegion("30247");
    }


    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            addToBot(new MakeTempCardInHandAction(new ShadowRose(), this.amount, false));
        }
    }


    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }


    public void updateDescription() {
        if (this.amount > 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}


