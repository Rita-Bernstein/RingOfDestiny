package RingOfDestiny.powers.Monster.BlackNoah;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.dungeons.*;


public class ReflectionPower
  extends AbstractPower implements CloneablePowerInterface
{
	public static final String POWER_ID = RingOfDestiny.makeID("Reflection");
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("Reflection"));
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    private boolean justApplied;

	public ReflectionPower(AbstractCreature owner) {
		this (owner, 1, false);
	}
	public ReflectionPower(AbstractCreature owner, final int amount) {
		this (owner, amount, false);
	}
	public ReflectionPower(AbstractCreature owner, final int amount, final boolean justApplied)
	{
		this.name = NAME;
		this.ID = RingOfDestiny.makeID("Reflection");
		this.owner = owner;
		this.amount = amount;
		this.justApplied = justApplied;
		this.type = PowerType.BUFF;
        this.isTurnBased = true;
		this.description = DESCRIPTIONS[0];
		//loadRegion("buffer");
		this.img = new Texture(RingOfDestiny.assetPath("img/powers/Reflection.png"));
	}


  
	@Override
	public void updateDescription()
	{
		this.description = DESCRIPTIONS[0];
	}
  
    @Override
    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (this.amount == 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, RingOfDestiny.makeID("Reflection")));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, RingOfDestiny.makeID("Reflection"), 1));
        }
    }
	
    @Override
    public int onAttacked(final DamageInfo info, final int damageAmount) {
        if (info.owner != null && info.owner != this.owner && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount <= 0 && info.output > 0) {
            this.flash();
            AbstractDungeon.actionManager.addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, info.output, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
        }
        return damageAmount;
    }
	@Override
	public AbstractPower makeCopy() {
		return new ReflectionPower(this.owner, this.amount, justApplied);
	}
}
