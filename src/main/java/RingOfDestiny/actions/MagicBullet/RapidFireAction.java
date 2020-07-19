 package RingOfDestiny.actions.MagicBullet;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 
 public class RapidFireAction
   extends AbstractGameAction {
   private DamageInfo info;
   private String power;

   public RapidFireAction(AbstractCreature target, DamageInfo info,String power) {
     this.actionType = AbstractGameAction.ActionType.BLOCK;
     this.target = target;
     this.info = info;
     this.power = power;
   }
 
   
   public void update() {
     if (this.target != null && this.target.hasPower(this.power)) {
       addToTop(new DrawCardAction(AbstractDungeon.player, 1));
       addToTop(new GainEnergyAction(1));
     } 
     
     addToTop(new DamageAction(this.target, this.info, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     this.isDone = true;
   }
 }


