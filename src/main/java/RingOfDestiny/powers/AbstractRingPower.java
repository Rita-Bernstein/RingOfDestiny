package RingOfDestiny.powers;


import RingOfDestiny.patches.AbstractRingPowerPatches;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Fasting;
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

    public void onEnemyDeath(AbstractMonster m) {
    }

    public void onDestructive() {
    }


    public String getCantPlayMessage() {
        return "";
    }

    public void onRefreshHandLayout() {

    }

    protected void loadRingRegion(String fileName) {
        this.region48 = AbstractRingPowerPatches.PatchEnergyPanelField.ringAtlas.get(this).findRegion("48/" + fileName);
        this.region128 = AbstractRingPowerPatches.PatchEnergyPanelField.ringAtlas.get(this).findRegion("128/" + fileName);
    }

}


