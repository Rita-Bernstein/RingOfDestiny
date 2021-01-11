package RingOfDestiny.powers.Monster.Ending;

import RingOfDestiny.RingOfDestiny;

import RingOfDestiny.monster.Ending.Bronze_Statue;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;

public class ArtGalleryPower extends AbstractPower implements CloneablePowerInterface
{
	public static final String POWER_ID = RingOfDestiny.makeID("ArtGallery");
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(RingOfDestiny.makeID("ArtGallery"));
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    private boolean justApplied;

	public ArtGalleryPower(AbstractCreature owner) {
		this (owner, 1, false);
	}

	public ArtGalleryPower(AbstractCreature owner, final int amount) {
		this (owner, amount, false);
	}

	public ArtGalleryPower(AbstractCreature owner, final int amount, final boolean justApplied)
	{
		this.name = NAME;
		this.ID = RingOfDestiny.makeID("ArtGallery");
		this.owner = owner;
		this.amount = amount;
		this.justApplied = justApplied;
		//this.type = PowerType.BUFF;
        this.isTurnBased = false;
		this.description = DESCRIPTIONS[0];
		loadRegion("buffer");

	}

  
	@Override
	public void updateDescription()
	{
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
	}
  
    @Override
    public void atEndOfRound() {
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (this.amount  == 2) {
        	//if(AbstractDungeon.getMonsters().monsters.size() <= 2){
				if (AbstractDungeon.getMonsters().monsters.get(0).isDead ){
					AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new Bronze_Statue( -300.0F, 0.0F,0), true,0));

					this.flash();
				}
				if (AbstractDungeon.getMonsters().monsters.get(1).isDead ){
					AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(new Bronze_Statue( 200.0F, 0.0F,1), true,1));

					this.flash();
				}
			//}

			this.amount = 0;
        }
		this.amount += 1;
    }
	

	@Override
	public AbstractPower makeCopy() {
		return new ArtGalleryPower(this.owner, this.amount, justApplied);
	}
}
