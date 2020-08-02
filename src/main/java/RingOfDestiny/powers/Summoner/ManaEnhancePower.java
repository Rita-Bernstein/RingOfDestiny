package RingOfDestiny.powers.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.ShadowFlower.ShadowRose;
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

public class ManaEnhancePower extends AbstractPower {
    public static final String POWER_ID = RingOfDestiny.makeID("ManaEnhancePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ManaEnhancePower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("flameBarrier");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        if(card.type == AbstractCard.CardType.ATTACK){
            if(!AbstractDungeon.player.hasPower(UpgradedManaEnhancePower.POWER_ID))
            addToTop(new ReducePowerAction(this.owner,this.owner,ManaEnhancePower.POWER_ID,1));
        }
    }

    public static int changeCost(int cost){
        int newCost = cost;
        if(newCost > 0) {
            newCost -=1;
        }
        return newCost;
    }

    public void atStartOfTurn() {
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, EtchReflectionPower.POWER_ID));
    }
}


