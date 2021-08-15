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
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;


public class LightAndDarknessPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("LightAndDarknessPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("LightAndDarknessPower"));
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public LightAndDarknessPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = RingOfDestiny.makeID("LightAndDarknessPower");
        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = false;
        updateDescription();
        loadRingRegion("2030328");
    }

    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (!m.isDying && !m.isEscaping) {
                if ((this.owner instanceof LightEmissary && m instanceof DarkEmissary) || (this.owner instanceof DarkEmissary && m instanceof LightEmissary))
                    addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount)));
            }
        }
    }
}
