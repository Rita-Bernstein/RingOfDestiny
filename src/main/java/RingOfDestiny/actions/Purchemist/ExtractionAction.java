package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.powers.BleedingPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class ExtractionAction extends AbstractGameAction {
    private int amount;
    private AbstractPlayer p;

    public ExtractionAction(AbstractPlayer p, int amount) {
        this.amount = amount;
        this.p = p;
        this.actionType = ActionType.DEBUFF;
    }


    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList<AbstractCard>();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE) {
                cardsToExhaust.add(c);
            }
        }

        int count = 0;
        for (AbstractCard c : cardsToExhaust) {
            count++;
        }

        if (count > 0)
            addToBot(new TempIncreaseMaxHPAction(count * this.amount, this.p));

        for (AbstractCard c : cardsToExhaust) {
            addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }
        this.isDone = true;
    }
}


