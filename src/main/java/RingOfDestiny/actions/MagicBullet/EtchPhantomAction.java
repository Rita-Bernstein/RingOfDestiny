package RingOfDestiny.actions.MagicBullet;

import RingOfDestiny.RingOfDestiny;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class EtchPhantomAction
        extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("EtchPhantomAction"))).TEXT;
    private AbstractPlayer player;
    private int numberOfCards;

    public EtchPhantomAction(int numberOfCards) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.player = AbstractDungeon.player;
        this.numberOfCards = numberOfCards;
    }



    public void update() {
        if (this.duration == this.startDuration) {
            if (this.player.drawPile.isEmpty() || this.numberOfCards <= 0) {
                this.isDone = true;
                return;
            }
            if (this.player.drawPile.size() <= this.numberOfCards) {
                ArrayList<AbstractCard> cardsToCopy = new ArrayList<AbstractCard>();
                for (AbstractCard c : this.player.drawPile.group) {
                    cardsToCopy.add(c);
                }
                for (AbstractCard c : cardsToCopy) {
                    addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
                }

                this.isDone = true;
                return;
            }


            CardGroup temp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.player.drawPile.group) {
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
                addToTop(new MakeTempCardInHandAction(c.makeStatEquivalentCopy()));
            }

            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        tickDuration();
    }
}


