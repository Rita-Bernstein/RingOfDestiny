package RingOfDestiny.powers.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.StrengthToMetallicizeAction;
import RingOfDestiny.cards.ShadowFlower.ShadowRose;
import RingOfDestiny.powers.AbstractRingPower;
import RingOfDestiny.powers.EtchPower;
import RingOfDestiny.powers.EtchReflectionPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class HegemonyOfDevilPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("HegemonyOfDevilPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HegemonyOfDevilPower(AbstractCreature owner) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        updateDescription();
        loadRegion("flameBarrier");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToTop(new RemoveSpecificPowerAction(this.owner,this.owner,HegemonyOfDevilPower.POWER_ID));
        addToTop(new StrengthToMetallicizeAction());

    }
}


