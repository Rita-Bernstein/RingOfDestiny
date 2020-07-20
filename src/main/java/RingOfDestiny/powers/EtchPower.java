package RingOfDestiny.powers;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.vfx.combat.FlashPowerEffect;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;


public class EtchPower extends TwoAmountPower implements CloneablePowerInterface {
    public static final String POWER_ID = RingOfDestiny.makeID("EtchPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int limite = 4;


    public EtchPower(AbstractCreature owner, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.amount2 = 30;
        this.type = PowerType.DEBUFF;
        updateDescription();
        loadRegion("phantasmal");
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        if(AbstractDungeon.player.hasPower(EtchSpiritPower.POWER_ID))
            this.amount2 = 30 + AbstractDungeon.player.getPower(EtchSpiritPower.POWER_ID).amount;
        triggerEtch();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        super.onApplyPower(power, target, source);
        if(AbstractDungeon.player.hasPower(EtchSpiritPower.POWER_ID))
            this.amount2 = 30 + AbstractDungeon.player.getPower(EtchSpiritPower.POWER_ID).amount;
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if(AbstractDungeon.player.hasPower(EtchSpiritPower.POWER_ID))
            this.amount2 = 30 + AbstractDungeon.player.getPower(EtchSpiritPower.POWER_ID).amount;
        triggerEtch();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    public void triggerEtch() {
        if (this.amount >= limite) {
            for (int i = limite; i <= this.amount; ) {
                //扣血
                flash();
                addToBot(new LoseHPAction(this.owner, null, this.amount2, AbstractGameAction.AttackEffect.FIRE));
                this.amount -= i;
            }
        }

        if (this.amount <= 0) addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, EtchPower.POWER_ID));
    }


    @Override
    public AbstractPower makeCopy() {
        return new EtchPower(this.owner, this.amount);
    }
}
