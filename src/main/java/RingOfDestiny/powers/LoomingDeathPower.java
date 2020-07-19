package RingOfDestiny.powers;

import RingOfDestiny.RingOfDestiny;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class LoomingDeathPower extends TwoAmountPower {
    public static final String POWER_ID = RingOfDestiny.makeID("LoomingDeathPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LoomingDeathPower(AbstractCreature owner, int amount, int amount2) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.amount2 = amount2;
        updateDescription();
        loadRegion("demonForm");
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2];
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.amount2 += stackAmount;
    }

    public void atStartOfTurnPostDraw() {
        flash();
        addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
        addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount2), this.amount2));
    }
}


