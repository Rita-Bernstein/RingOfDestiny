package RingOfDestiny.powers;
 
 import RingOfDestiny.RingOfDestiny;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.powers.AbstractPower;

public class BloodmournePower extends AbstractRingPower {
   public static final String POWER_ID = RingOfDestiny.makeID("BloodmournePower");
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public BloodmournePower(AbstractCreature owner,int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     updateDescription();
     loadRingRegion("4933");
   }
   
   public void updateDescription() {
           this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];

   }
 }


