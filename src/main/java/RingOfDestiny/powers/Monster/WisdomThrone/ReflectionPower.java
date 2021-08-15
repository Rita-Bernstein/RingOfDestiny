package RingOfDestiny.powers.Monster.WisdomThrone;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.monster.WisdomThrone.DarkEmissary;
import RingOfDestiny.monster.WisdomThrone.LightEmissary;
import RingOfDestiny.powers.AbstractRingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;


public class ReflectionPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("ReflectionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("ReflectionPower"));
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public ReflectionPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = RingOfDestiny.makeID("ReflectionPower");
        this.owner = owner;
        this.amount = -1;
        this.isTurnBased = false;
        updateDescription();
        loadRingRegion("2030325");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if(info.type == DamageInfo.DamageType.NORMAL)
        addToBot(new ApplyPowerAction(AbstractDungeon.player, this.owner, new SparklingLightPower(AbstractDungeon.player, 2)));
        return damageAmount;
    }
}
