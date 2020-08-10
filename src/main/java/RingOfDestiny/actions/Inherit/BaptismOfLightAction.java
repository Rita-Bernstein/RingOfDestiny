package RingOfDestiny.actions.Inherit;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.HealAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.vfx.WallopEffect;
 import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
 
 public class BaptismOfLightAction extends AbstractGameAction {
   private DamageInfo info;
   
   public BaptismOfLightAction(AbstractCreature target, DamageInfo info) {
     this.info = info;
     setValues(target, info);
     this.actionType = AbstractGameAction.ActionType.DAMAGE;
     this.startDuration = Settings.ACTION_DUR_FAST;
     this.duration = this.startDuration;
   }
 
   
   public void update() {
     if (shouldCancelAction()) {
       this.isDone = true;
       
       return;
     } 
     tickDuration();
     
     if (this.isDone) {
       AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.BLUNT_HEAVY, false));
       
       this.target.damage(this.info);
       if (this.target.lastDamageTaken > 0) {
         addToTop(new HealAction(this.source,this.source, this.target.lastDamageTaken));
         if (this.target.hb != null) {
           addToTop(new VFXAction(new WallopEffect(this.target.lastDamageTaken, this.target.hb.cX, this.target.hb.cY)));
         }
       } 
       
       if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
         AbstractDungeon.actionManager.clearPostCombatActions();
       } else {
         addToTop(new WaitAction(0.1F));
       } 
     } 
   }
 }


