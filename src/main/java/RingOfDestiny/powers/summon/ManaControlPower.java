package RingOfDestiny.powers.summon;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.AddSoulStoneAction;
import RingOfDestiny.cards.ShadowFlower.ShadowRose;
import RingOfDestiny.patches.CorruptionPatches;
import RingOfDestiny.powers.AbstractRingPower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ManaControlPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("ManaControlPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ManaControlPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 0;
        updateDescription();
        loadRingRegion("1030375");
    }

    @Override
    public void onGainedBlock(float blockAmount) {
        flash();
        this.amount += blockAmount;
        if (this.amount > 20) {
            int ten = this.amount / 20;
            addToBot(new AddSoulStoneAction(ten));
            this.amount = this.amount % 20;
        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if(card.type == AbstractCard.CardType.SKILL && card.color == AbstractDungeon.player.getCardColor()){
            CorruptionPatches.setCostForTurnManaControl(card);
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}


