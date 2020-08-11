package RingOfDestiny.powers.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.ShadowFlower.ShadowRose;
import RingOfDestiny.cards.Summoner.*;
import RingOfDestiny.powers.AbstractRingPower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class DoomsdayImprintPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("DoomsdayImprintPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DoomsdayImprintPower(AbstractCreature owner,int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        if(this.amount > 10)this.amount = 10;
        updateDescription();
        loadRingRegion("1010338");
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        if(this.amount > 10)this.amount = 10;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if(this.amount > 10)this.amount = 10;
    }


    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (type == DamageInfo.DamageType.NORMAL && (card.cardID.equals(DoomsdayMeteorite.ID) || card.cardID.equals(DoomsdayLava.ID) )) {
            return damage + this.amount;
        }

        if (type == DamageInfo.DamageType.NORMAL && card.cardID.equals(DoomsdayBlade.ID)) {
            return damage + card.magicNumber;
        }

        return damage;
    }




    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}


