package RingOfDestiny.powers;


import RingOfDestiny.patches.AbstractRingPowerPatches;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.purple.Fasting;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractRingPower extends AbstractPower {

    public static TextureAtlas ringAtlas;

    public static void initialize() {
        ringAtlas = new TextureAtlas(Gdx.files.internal("RingOfDestiny/powers/ringPowers.atlas"));
    }

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

    public void onUseDiamond(){}

    protected void loadRingRegion(String fileName) {
        this.region48  = AbstractRingPower.ringAtlas.findRegion("48/" + fileName);
        this.region128 = AbstractRingPower.ringAtlas.findRegion("128/" + fileName);
    }

}


