package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.Purchemist.DrawAgain;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class DrawAgainAction
        extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("DrawAgainAction"))).TEXT;
    private AbstractPlayer player;
    private int numberOfCards;

    public DrawAgainAction(int numberOfCards) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
    }


    public void update() {
        if (this.duration == this.startDuration) {
            int amount = 0;
            for (AbstractCard c : this.player.drawPile.group) {
                if (c instanceof DrawAgain) amount++;
            }
            if (amount == 0 || this.numberOfCards <= 0) {
                this.isDone = true;
                return;
            }
            if (this.player.drawPile.size() <= this.numberOfCards) {
                ArrayList<AbstractCard> cardsToMove = new ArrayList<AbstractCard>();
                for (AbstractCard c : this.player.drawPile.group) {
                    if (c instanceof DrawAgain)
                        cardsToMove.add(c);
                }
                for (AbstractCard c : cardsToMove) {
                    if (this.player.hand.size() == 10) {
                        this.player.drawPile.moveToDiscardPile(c);
                        this.player.createHandIsFullDialog();
                        continue;
                    }
                    this.player.drawPile.moveToHand(c, this.player.drawPile);
                }

                this.isDone = true;
                return;
            }


            CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.player.drawPile.group) {
                if (c instanceof DrawAgain)
                    temp.addToTop(c);
            }
            temp.sortAlphabetically(true);
            temp.sortByRarityPlusStatusCardType(false);
            if (this.numberOfCards == 1) {
                AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, TEXT[0], false);
            } else {
                AbstractDungeon.gridSelectScreen.open(temp, this.numberOfCards, TEXT[1] + this.numberOfCards + TEXT[2], false);
            }

            tickDuration();
            return;
        }
        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                if (this.player.hand.size() == 10) {
                    this.player.drawPile.moveToDiscardPile(c);
                    this.player.createHandIsFullDialog();
                    continue;
                }
                this.player.drawPile.moveToHand(c, this.player.drawPile);
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }
}


