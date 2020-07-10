package RingOfDestiny.powers;
 
 import RingOfDestiny.RingOfDestiny;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.powers.AbstractPower;

public class GasBombPower extends AbstractPower {
   public static final String POWER_ID = RingOfDestiny.makeID("GasBombPower");
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public GasBombPower(AbstractCreature owner,int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     updateDescription();
     loadRegion("barricade");
   }

     public void stackPower(int stackAmount) {
         this.fontScale = 8.0F;
         this.amount += stackAmount;
     }
   
   public void updateDescription() {
           this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];

   }
 }


