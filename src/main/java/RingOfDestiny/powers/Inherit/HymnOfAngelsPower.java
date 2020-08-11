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


public class HymnOfAngelsPower extends AbstractRingPower {
    public static final String POWER_ID = RingOfDestiny.makeID("HymnOfAngelsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HymnOfAngelsPower(AbstractCreature owner, int amount) {
        this.name = powerStrings.NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRingRegion("1030335");
    }


    public void onCardDraw(AbstractCard card) {
        if (card.type == AbstractCard.CardType.SKILL) {
            card.setCostForTurn(0);
        }
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.SKILL)
                c.setCostForTurn(0);
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.type == AbstractCard.CardType.SKILL)
                c.setCostForTurn(0);
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.type == AbstractCard.CardType.SKILL)
                c.setCostForTurn(0);
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.type == AbstractCard.CardType.SKILL)
                c.setCostForTurn(0);
        }
    }


    @Override
    public void atStartOfTurnPostDraw() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.SKILL)
                c.setCostForTurn(0);
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.type == AbstractCard.CardType.SKILL)
                c.setCostForTurn(0);
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.type == AbstractCard.CardType.SKILL)
                c.setCostForTurn(0);
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c.type == AbstractCard.CardType.SKILL)
                c.setCostForTurn(0);
        }
    }

    @Override
    public void onRefreshHandLayout() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.SKILL)
                c.setCostForTurn(0);
        }
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
        addToBot(new ReducePowerAction(this.owner, this.owner, HymnOfAngelsPower.POWER_ID, 1));
    }
}


