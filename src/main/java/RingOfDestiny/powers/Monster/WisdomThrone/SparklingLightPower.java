package RingOfDestiny.powers.Monster.WisdomThrone;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.monster.WisdomThrone.DarkEmissary;
import RingOfDestiny.monster.WisdomThrone.LightEmissary;
import RingOfDestiny.powers.AbstractRingPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class SparklingLightPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("SparklingLightPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("SparklingLightPower"));
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int count = 0;

    public SparklingLightPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = RingOfDestiny.makeID("SparklingLightPower");
        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = true;
        this.type = PowerType.DEBUFF;
        updateDescription();
        loadRingRegion("2010269");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
        return super.atDamageReceive(damage, damageType, card);
    }

    @Override
    public float atSingleDamageGive(AbstractMonster m, float damage, DamageInfo.DamageType type) {
        if (m instanceof DarkEmissary)
            return damage * 2.0f;
        else
            return super.atSingleDamageGive(m, damage, type);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.owner.name.equals(DarkEmissary.NAME))
            return damageAmount * 2;
        return damageAmount;
    }

    @Override
    public void atEndOfRound() {
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, SparklingLightPower.POWER_ID));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, SparklingLightPower.POWER_ID, 1));
        }
    }
}
