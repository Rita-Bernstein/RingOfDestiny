package RingOfDestiny.powers.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.ShadowFlower.ShadowRose;
import RingOfDestiny.powers.AbstractRingPower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ExtractPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("ExtractPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int limite = 2;
    private static int healingAmount = 1;
    private AbstractCreature source ;

    public ExtractPower(AbstractCreature owner,AbstractCreature source, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.source = source;
        this.type =PowerType.DEBUFF;
        updateDescription();
        loadRingRegion("1010331");
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        triggerHealing();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        triggerHealing();
    }

    public void triggerHealing() {
        if (this.amount >= limite) {
            for (int i = limite; i <= this.amount; ) {
                //扣血
                flash();
                addToTop(new HealAction(this.source, this.owner, 1));
                this.amount -= 2;
            }
        }

        if (this.amount <= 0) addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, ExtractPower.POWER_ID));
    }

    public void updateDescription() {
        if(this.owner.isPlayer){
            this.description = DESCRIPTIONS[1] + healingAmount + DESCRIPTIONS[2];
        }else {
            this.description = DESCRIPTIONS[0] + healingAmount + DESCRIPTIONS[2];
        }

    }
}


