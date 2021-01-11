package RingOfDestiny.powers.Monster.Ending;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.dungeons.*;



public class SelfDetonatePower extends AbstractPower {
	public static final String POWER_ID = RingOfDestiny.makeID("SelfDetonate");
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("SelfDetonate"));
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;



	public SelfDetonatePower(AbstractCreature owner)
	{
		this.name = NAME;
		this.ID = RingOfDestiny.makeID("SelfDetonate");
		this.owner = owner;
		this.amount = -1;
		//this.type = PowerType.BUFF;
		this.isTurnBased = false;
		this.description = DESCRIPTIONS[0];
		loadRegion("explosive");
		//this.img = new Texture(RingOfDestiny.assetPath("images/powers/Reflection.png"));
	}


    @Override
    public int onAttacked(final DamageInfo info, final int damageAmount) {
        if (info.owner != this.owner && info.output > this.owner.currentHealth  ) {
            this.flash();
            AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.player, new DamageInfo(this.owner, this.owner.currentHealth, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE, true));
        }
        return damageAmount;
    }
}
