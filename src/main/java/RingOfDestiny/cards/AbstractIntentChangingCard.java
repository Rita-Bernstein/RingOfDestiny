 package RingOfDestiny.cards;
 import RingOfDestiny.actions.ShadowFlower.ForceIntentAction;
 import RingOfDestiny.vfx.ForceIntentChangePreviewEffect;
 import basemod.ReflectionHacks;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
 import java.util.ArrayList;
 import java.util.Iterator;
 
 public abstract class AbstractIntentChangingCard extends AbstractDefaultCard {
   public IntentTypes intentType;
   
   public enum IntentTypes {
     ATTACK,
     NOT_ATTACK;
   }
 
 
   
   public ArrayList<EnemyMoveInfo> enemyMoves = new ArrayList();
   public EnemyMoveInfo move;
   public EnemyMoveInfo nextMove;
   public AbstractMonster newTarget;
   public boolean intentRevert = false;
   public static long songID = 0L;
 
 
 
 
 
 
 
 
   
   public AbstractIntentChangingCard(String id, String img, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, IntentTypes intentType) {
     super(id, img, cost, type, color, rarity, target);
     this.intentType = intentType;
   }
 
   
   public void calculateCardDamage(AbstractMonster mo) {
     super.calculateCardDamage(mo);
     if (this.target == AbstractCard.CardTarget.ALL_ENEMY) {
       if (this.newTarget == null) {
         ArrayList monsters = (AbstractDungeon.getCurrRoom()).monsters.monsters;
         for (int i = 0; i < monsters.size(); i++) {
           AbstractMonster m = (AbstractMonster)monsters.get(i);
           this.enemyMoves.add((EnemyMoveInfo)ReflectionHacks.getPrivate(m, AbstractMonster.class, "move"));
           if (!m.isDeadOrEscaped() && !m.halfDead) {
             this.newTarget = m;
 
             
             int counter = AbstractDungeon.aiRng.counter;
             long seed0 = ((Long)ReflectionHacks.getPrivate(AbstractDungeon.aiRng.random, com.badlogic.gdx.math.RandomXS128.class, "seed0")).longValue();
             long seed1 = ((Long)ReflectionHacks.getPrivate(AbstractDungeon.aiRng.random, com.badlogic.gdx.math.RandomXS128.class, "seed1")).longValue();
             
             ForceIntentAction.previewNewIntent(this.newTarget, this.intentType);
             
             AbstractDungeon.aiRng.counter = counter;
             AbstractDungeon.aiRng.random.setState(seed0, seed1);
           } 
         } 
       } 
     } else if (this.target == AbstractCard.CardTarget.ENEMY && mo != null && 
       this.newTarget == null) {
       this.newTarget = mo;
       this.move = (EnemyMoveInfo)ReflectionHacks.getPrivate(mo, AbstractMonster.class, "move");
       
       int counter = AbstractDungeon.aiRng.counter;
       long seed0 = ((Long)ReflectionHacks.getPrivate(AbstractDungeon.aiRng.random, com.badlogic.gdx.math.RandomXS128.class, "seed0")).longValue();
       long seed1 = ((Long)ReflectionHacks.getPrivate(AbstractDungeon.aiRng.random, com.badlogic.gdx.math.RandomXS128.class, "seed1")).longValue();
       
       ForceIntentAction.previewNewIntent(this.newTarget, this.intentType);
       
       AbstractDungeon.aiRng.counter = counter;
       AbstractDungeon.aiRng.random.setState(seed0, seed1);
     } 
 
 
     
     this.intentRevert = false;
   }
 
   
   public void update() {
     super.update();
     if (this.target == AbstractCard.CardTarget.ALL_ENEMY) {
       if (this.newTarget != null && !this.intentRevert) {
         Iterator iterator = (AbstractDungeon.getCurrRoom()).monsters.monsters.iterator();
         while (iterator.hasNext()) {
           AbstractMonster m = (AbstractMonster)iterator.next();
           if (!m.isDeadOrEscaped() && !m.halfDead) {
             this.newTarget = m;
             AbstractDungeon.effectsQueue.add(new ForceIntentChangePreviewEffect(this.newTarget.intentHb.cX, this.newTarget.intentHb.cY, 0.75F, 1.75F));
           } 
         } 
       } 
     } else if (this.target == AbstractCard.CardTarget.ENEMY && 
       this.newTarget != null && !this.intentRevert) {
       AbstractDungeon.effectsQueue.add(new ForceIntentChangePreviewEffect(this.newTarget.intentHb.cX, this.newTarget.intentHb.cY, 0.75F, 1.75F));
     } 
 
 
     
     if (this.target == AbstractCard.CardTarget.ALL_ENEMY) {
       if (this.newTarget != null && this.intentRevert) {
         ArrayList monsters = (AbstractDungeon.getCurrRoom()).monsters.monsters;
         for (int i = 0; i < monsters.size(); i++) {
           AbstractMonster m = (AbstractMonster)monsters.get(i);
           if (!m.isDeadOrEscaped() && !m.halfDead) {
             this.newTarget = m;
             this.newTarget.moveHistory.remove(this.newTarget.moveHistory.size() - 1);
             if (this.newTarget.moveHistory.size() > 0) {
               this.newTarget.moveHistory.remove(this.newTarget.moveHistory.size() - 1);
             }
             
             EnemyMoveInfo move = (EnemyMoveInfo)this.enemyMoves.get(i);
             this.newTarget.setMove(move.nextMove, move.intent, move.baseDamage, move.multiplier, move.isMultiDamage);
             this.newTarget.createIntent();
             
             this.newTarget = null;
           } 
         } 
         this.enemyMoves.clear();
       } 
     } else if (this.target == AbstractCard.CardTarget.ENEMY && 
       this.newTarget != null && this.intentRevert) {
       
       this.newTarget.moveHistory.remove(this.newTarget.moveHistory.size() - 1);
       if (this.newTarget.moveHistory.size() > 0) {
         this.newTarget.moveHistory.remove(this.newTarget.moveHistory.size() - 1);
       }
       
       this.newTarget.setMove(this.move.nextMove, this.move.intent, this.move.baseDamage, this.move.multiplier, this.move.isMultiDamage);
       this.newTarget.createIntent();
       
       this.newTarget = null;
       
       this.move = null;
     } 
     
     this.intentRevert = true;
   }
 }


