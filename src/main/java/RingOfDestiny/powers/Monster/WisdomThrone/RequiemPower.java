package RingOfDestiny.powers.Monster.WisdomThrone;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.MagicBullet.ExhaustAllNonSkillAction;
import RingOfDestiny.actions.monster.ExhaustAllDiscardPileAction;
import RingOfDestiny.powers.AbstractRingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
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


public class RequiemPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("RequiemPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("RequiemPower"));
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public RequiemPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = RingOfDestiny.makeID("RequiemPower");
        this.owner = owner;
        this.amount = -1;
        this.isTurnBased = false;
        updateDescription();
        loadRingRegion("4211");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void onDeath() {
        addToBot(new ExhaustAllDiscardPileAction());
    }
}
