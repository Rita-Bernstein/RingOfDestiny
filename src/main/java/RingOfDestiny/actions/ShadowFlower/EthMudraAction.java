package RingOfDestiny.actions.ShadowFlower;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
 
 public class EthMudraAction
   extends AbstractGameAction {
   private DamageInfo info;
   private static final float DURATION = 0.01F;
   private static final float POST_ATTACK_WAIT_DUR = 0.1F;
   private AbstractMonster m;
   
   public EthMudraAction(AbstractMonster target, DamageInfo info) {
     this.info = info;
     setValues(target, info);
     this.m = target;
     this.actionType = AbstractGameAction.ActionType.DAMAGE;
     this.attackEffect = AbstractGameAction.AttackEffect.SLASH_VERTICAL;
     this.duration = 0.01F;
   }
 
   
   public void update() {
     if (this.target == null) {
       this.isDone = true;
       return;
     } 
     if (this.m.hasPower("Poison")) {
         this.info.base = this.m.getPower("Poison").amount;
       if (this.duration == 0.01F && this.target != null && this.target.currentHealth > 0) {
         
         if (this.info.type != DamageInfo.DamageType.THORNS && this.info.owner.isDying) {
           this.isDone = true;
           
           return;
         } 
         AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
       } 
       
       tickDuration();
       
       if (this.isDone && this.target != null && this.target.currentHealth > 0) {
         this.target.damage(this.info);
         
         if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
           AbstractDungeon.actionManager.clearPostCombatActions();
         }
         addToTop(new WaitAction(0.1F));
       } 
     } else {
       
       this.isDone = true;
     } 
   }
 }


