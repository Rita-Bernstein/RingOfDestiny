package RingOfDestiny.powers;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.MagicBullet.Residual;
import RingOfDestiny.patches.AbstractRingPowerPatches;
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
        this.region48 = AbstractRingPowerPatches.PatchEnergyPanelField.ringAtlas.get(this).findRegion("48/" + "4918");
        this.region128 = AbstractRingPowerPatches.PatchEnergyPanelField.ringAtlas.get(this).findRegion("128/" + "4918");
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        triggerEtch();
    }


    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        triggerEtch();
    }

    @Override
    public void update(int slot) {
        if(AbstractDungeon.player.hasPower(EtchSpiritPower.POWER_ID)){
            this.amount2 = 30 + AbstractDungeon.player.getPower(EtchSpiritPower.POWER_ID).amount;
        }else {
            this.amount2 = 30;
        }

        if(AbstractDungeon.player.hasPower(DoubleEtchDamagePower.POWER_ID))
            this.amount2 *= 2;
        super.update(slot);
    }


    public void triggerEtch() {
        if (this.amount >= limite) {
            for (int i = limite; i <= this.amount; ) {
                //扣血
                flash();
                addToTop(new LoseHPAction(this.owner, null, this.amount2, AbstractGameAction.AttackEffect.FIRE));
                int remain = 0;
                if(AbstractDungeon.player.hasPower(ResidualPower.POWER_ID)) remain = AbstractDungeon.player.getPower(ResidualPower.POWER_ID).amount;
                this.amount -= (i - remain);
            }
        }

        if (this.amount <= 0) addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, EtchPower.POWER_ID));
    }


    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new EtchPower(this.owner, this.amount);
    }
}
