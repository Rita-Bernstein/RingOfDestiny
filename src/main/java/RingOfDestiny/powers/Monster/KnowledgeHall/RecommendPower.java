package RingOfDestiny.powers.Monster.KnowledgeHall;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.Purchemist.Recommend;
import RingOfDestiny.powers.AbstractRingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.StrengthPower;


public class RecommendPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("RecommendPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("RecommendPower"));
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int count = 0;

    public RecommendPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = RingOfDestiny.makeID("RecommendPower");
        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = false;
        updateDescription();
        loadRingRegion("4203");
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new MakeTempCardInDrawPileAction(new Recommend(),this.amount,true,true));
    }
}
