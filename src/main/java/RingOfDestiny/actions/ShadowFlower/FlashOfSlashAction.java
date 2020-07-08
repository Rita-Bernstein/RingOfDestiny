 package RingOfDestiny.actions.ShadowFlower;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.UIStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.PoisonPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.vfx.ThoughtBubble;
 
 public class FlashOfSlashAction extends AbstractGameAction {
   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("OpeningAction");
   public static final String[] TEXT = uiStrings.TEXT;
   
   private int damageIncrease;
   private AbstractMonster targetMonster;
   
   public FlashOfSlashAction(int damageIncrease, AbstractMonster m) {
     this.duration = 0.0F;
     this.actionType = AbstractGameAction.ActionType.WAIT;
     this.damageIncrease = damageIncrease;
     this.targetMonster = m;
   }
 
   
   public void update() {
     if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() >= 0) {
       addToBot(new ApplyPowerAction(this.targetMonster, AbstractDungeon.player, new PoisonPower(this.targetMonster, AbstractDungeon.player,this.damageIncrease), this.damageIncrease));
 
     
     }
     else {
 
       
       AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[0], true));
     } 
 
     
     this.isDone = true;
   }
 }


