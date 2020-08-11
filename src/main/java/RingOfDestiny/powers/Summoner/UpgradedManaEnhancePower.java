package RingOfDestiny.powers.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.ShadowFlower.ShadowRose;
import RingOfDestiny.powers.AbstractRingPower;
import RingOfDestiny.powers.EtchPower;
import RingOfDestiny.powers.EtchReflectionPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class UpgradedManaEnhancePower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("UpgradedManaEnhancePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public UpgradedManaEnhancePower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRingRegion("1030361");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        if(card.type == AbstractCard.CardType.ATTACK){
            addToTop(new ReducePowerAction(this.owner,this.owner,UpgradedManaEnhancePower.POWER_ID,1));
        }
    }

    public static int changeCost(int cost){
        int newCost = cost;
        if(newCost > 1) {
            newCost -=2;
        }else if(newCost > 0){
            newCost = 0;
        }
        return newCost;
    }

    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, UpgradedManaEnhancePower.POWER_ID));
    }
}


