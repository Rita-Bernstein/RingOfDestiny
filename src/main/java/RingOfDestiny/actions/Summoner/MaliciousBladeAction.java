package RingOfDestiny.actions.Summoner;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Summoner.ExtractPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MaliciousBladeAction extends AbstractGameAction {
    private int amount;
    private static final float DURATION = 0.01F;
    private static final float POST_ATTACK_WAIT_DUR = 0.1F;
    private int numTimes;
    private int damage;
    private DamageInfo.DamageType damageTypeForTurn;

    public MaliciousBladeAction(AbstractCreature target, int amount, int numTimes, int damage, DamageInfo.DamageType damageTypeForTurn) {
        this.target = target;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.01F;
        this.numTimes = numTimes;
        this.damage = damage;
        this.damageTypeForTurn = damageTypeForTurn;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
    }


       public void update() {
             if (this.target == null) {
                   this.isDone = true;
            
                   return;
                 }
             if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                   AbstractDungeon.actionManager.clearPostCombatActions();
                   this.isDone = true;
            
                   return;
                 }
        
             if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                   this.numTimes--;
                   AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            
            
            
                   addToTop(new MaliciousBladeAction(randomMonster, this.amount, this.numTimes,this.damage, this.damageTypeForTurn));
                 }
        
        
        
        
             if (this.target.currentHealth > 0) {
                 addToTop(new DamageAction(this.target,
                         new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
                   addToTop(new ApplyPowerAction(this.target, AbstractDungeon.player, new ExtractPower(this.target, AbstractDungeon.player, this.amount), this.amount, true, AbstractGameAction.AttackEffect.POISON));

                 }
        
             this.isDone = true;
           }
}


