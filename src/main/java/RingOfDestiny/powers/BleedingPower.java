package RingOfDestiny.powers;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.BleedingLoseHpAction;
import RingOfDestiny.cards.ShadowFlower.ShadowRose;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BleedingPower extends AbstractPower implements HealthBarRenderPower {
    public static final String POWER_ID = RingOfDestiny.makeID("BleedingPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private AbstractCreature source;

    public BleedingPower(AbstractCreature owner, AbstractCreature source, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.amount = amount;
        updateDescription();
        loadRegion("poison");
    }

    @Override
    public float atDamageFinalGive(float damage, DamageInfo.DamageType type) {
        return damage + this.amount;
    }


    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        addToTop(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
        return super.onAttackedToChangeDamage(info, damageAmount);
    }

    public void atStartOfTurn() {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flashWithoutSound();
            addToBot(new BleedingLoseHpAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.POISON));
        }
    }


    @Override
    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_POISON", 0.05F);
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public int getHealthBarAmount() {
        return this.amount;
    }

    @Override
    public Color getColor() {
        return Color.RED.cpy();
    }
}


