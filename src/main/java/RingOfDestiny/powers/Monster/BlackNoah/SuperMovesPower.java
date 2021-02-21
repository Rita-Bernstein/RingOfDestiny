package RingOfDestiny.powers.Monster.BlackNoah;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.monster.BlackNoah.Rita;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.*;


public class SuperMovesPower extends AbstractPower {
	public static final String POWER_ID = RingOfDestiny.makeID("SuperMoves");
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("SuperMoves"));
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;



	public SuperMovesPower(AbstractCreature owner)
	{
		this.name = NAME;
		this.ID = RingOfDestiny.makeID("SuperMoves");
		this.owner = owner;
		this.amount = -1;
		//this.type = PowerType.BUFF;
		this.isTurnBased = false;
		this.description = DESCRIPTIONS[0];
		loadRegion("focus");
		//this.img = new Texture(RingOfDestiny.assetPath("images/powers/Reflection.png"));
	}


    @Override
	public void  atEndOfRound() {
		//if ( ((Rita) this.owner).powerMeter >= ((Rita) this.owner).maxPowerMeter && ((Rita) this.owner).halfDead == false){
			if (((Rita) this.owner).superMoves == true){

			flash();
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new IntangiblePlayerPower(this.owner, 1)));
				((Rita) this.owner).superMoves = false;
				//AbstractDungeon.actionManager.addToTop((new ShoutAction(this.owner, ((Rita) this.owner).superMoves+"")));
			}
		//}

    }
}
