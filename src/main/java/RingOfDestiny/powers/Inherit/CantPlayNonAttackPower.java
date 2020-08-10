package RingOfDestiny.powers.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.Inherit.HolyJustice;
import RingOfDestiny.powers.AbstractRingPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class CantPlayNonAttackPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("CantPlayNonAttackPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static final String[] cantPlayCard = CardCrawlGame.languagePack.getUIString("CantPlayCardPowerPatch").TEXT;

    public CantPlayNonAttackPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        updateDescription();
        loadRegion("entangle");
    }

    public void playApplyPowerSfx() { CardCrawlGame.sound.play("POWER_ENTANGLED", 0.05F); }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        return (card.type == AbstractCard.CardType.ATTACK);
    }

    @Override
    public String getCantPlayMessage() {
        return cantPlayCard[0];
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new ReducePowerAction(this.owner, this.owner, CantPlayNonAttackPower.POWER_ID, 1));
    }
}


