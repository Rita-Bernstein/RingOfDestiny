package RingOfDestiny.powers;


import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractRingPower extends AbstractPower {

    public AbstractRingPower() {
        super();
    }

    public void onSummonAttack() {

    }

    public void onManualDiscard() {
    }

    public void VoodooDollLoseHP(int amount) {
        addToTop(new LoseHPAction(this.owner, null, amount * this.amount));
    }

    public void onEnemyDeath(AbstractMonster m){
    }

    public void onDestructive(){}

}


