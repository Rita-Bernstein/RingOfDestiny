package RingOfDestiny.powers.Monster.Ending;

import RingOfDestiny.RingOfDestiny;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import basemod.interfaces.CloneablePowerInterface;

public class ProbePower
        extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = RingOfDestiny.makeID("Probe");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("Probe"));
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    private boolean justApplied;

    public ProbePower(AbstractCreature owner) {
        this(owner, 1, false);
    }

    public ProbePower(AbstractCreature owner, final int amount) {
        this(owner, amount, false);
    }

    public ProbePower(AbstractCreature owner, final int amount, final boolean justApplied) {
        this.name = NAME;
        this.ID = RingOfDestiny.makeID("Probe");
        this.owner = owner;
        this.amount = amount;
        this.justApplied = justApplied;

        this.isTurnBased = false;
        this.description = DESCRIPTIONS[0];
        loadRegion("fading");

    }


    @Override
    public void updateDescription() {
        if (this.amount == 1) {

            this.description = DESCRIPTIONS[2];

        } else {

            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];

        }

    }

    @Override
    public void duringTurn() {
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (this.amount == 1 && !this.owner.isDying) {
            AbstractDungeon.actionManager.addToTop(new DamageAction(this.owner, new DamageInfo(this.owner, this.owner.currentHealth, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE, true));
        }
        this.amount -= 1;
    }


    @Override
    public AbstractPower makeCopy() {
        return new ReflectionPower(this.owner, this.amount, justApplied);
    }
}
