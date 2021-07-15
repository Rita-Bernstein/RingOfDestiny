package RingOfDestiny.powers.Monster.WisdomThrone;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.powers.AbstractRingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


public class KeepOffRainPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("KeepOffRainPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("KeepOffRainPower"));
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int triggerStack;

    public KeepOffRainPower(AbstractCreature owner,int triggerStack) {
        this.name = NAME;
        this.ID = RingOfDestiny.makeID("KeepOffRainPower");
        this.owner = owner;
        this.triggerStack = triggerStack;
        this.amount = triggerStack;
        this.isTurnBased = false;
        updateDescription();
        loadRingRegion("2030318");
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0],amount) ;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.type == AbstractCard.CardType.ATTACK)
            this.amount--;

        if(amount <= 0){
            addToBot(new ApplyPowerAction(this.owner, this.owner, new IntangiblePlayerPower(this.owner, 1),1));
            amount = this.triggerStack;
        }

    }
}
