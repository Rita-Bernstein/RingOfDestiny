package RingOfDestiny.actions.Purchemist;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AllCostAndTypeToHandAction extends AbstractGameAction {
    private AbstractPlayer p;
    private int costTarget;
    private AbstractCard.CardType cardType;

    public AllCostAndTypeToHandAction(int costToTarget, AbstractCard.CardType cardType) {
        this.p = AbstractDungeon.player;
        setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.costTarget = costToTarget;
        this.cardType = cardType;
    }


    public void update() {
        if (this.p.discardPile.size() > 0) {
            for (AbstractCard card : this.p.discardPile.group) {
                if ((card.cost == this.costTarget || card.freeToPlayOnce) && card.type == this.cardType) {
                    addToBot(new DiscardToHandAction(card));
                }
            }
        }
        tickDuration();
        this.isDone = true;
    }
}


