package RingOfDestiny.powers.Monster.WisdomThrone;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.powers.AbstractRingPower;
import RingOfDestiny.powers.BleedingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
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


public class CryingBloodPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("CryingBloodPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("CryingBloodPower"));
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public CryingBloodPower(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = RingOfDestiny.makeID("CryingBloodPower");
        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = false;
        updateDescription();
        loadRingRegion("4952");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new ApplyPowerAction(AbstractDungeon.player,this.owner,new BleedingPower(AbstractDungeon.player,this.owner,this.amount),this.amount));
        addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,CryingBloodPower.POWER_ID));
    }
}
