package RingOfDestiny.powers.Inherit;


import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.powers.AbstractRingPower;
import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnPlayerDeathPower;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;


public class ResurrectionGemPower extends AbstractRingPower implements OnPlayerDeathPower, CloneablePowerInterface {
    public static final String POWER_ID = RingOfDestiny.makeID("ResurrectionGem");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public ResurrectionGemPower(AbstractCreature owner, AbstractCreature source, int amount) {

        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("unawakened");

    }

    @Override
    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_AWAKENEDONE_1"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(this.owner, new IntenseZoomEffect(this.owner.hb.cX, this.owner.hb.cY, true), 0.05F, true));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, ResurrectionGemPower.POWER_ID));

        AbstractDungeon.actionManager.addToBottom(new HealAction(abstractPlayer, abstractPlayer, (int) Math.floor(AbstractDungeon.player.maxHealth * this.amount * 0.01f)));

        return false;
    }

    @Override
    public AbstractPower makeCopy() {
        return new ResurrectionGemPower(this.owner, this.owner, this.amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

}




