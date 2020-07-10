package RingOfDestiny.powers;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.ShadowFlower.ShadowRose;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class NinjutsuScrollPower extends AbstractPower {
    public static final String POWER_ID = RingOfDestiny.makeID("NinjutsuScrollPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NinjutsuScrollPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Dark Embrace";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("darkembrace");
    }


    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            addToBot(new MakeTempCardInHandAction(new ShadowRose(), this.amount, false));
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

       public void onExhaust(AbstractCard card) {
             if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                   flash();
                   addToBot(new GainEnergyAction(this.amount));
                 }
           }
}

