package RingOfDestiny.actions.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.CustomTagsEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;


public class ScepterOfGluttonyAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("ScepterOfGluttonyAction"));
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;
    private int amount;
    private ArrayList<AbstractCard> cannotDuplicate;

    public ScepterOfGluttonyAction(int amount) {
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.cannotDuplicate = new ArrayList();
    }

    public void update() {
        if (this.duration == 0.5F) {
            for (AbstractCard c : this.p.hand.group) {
                if (c.hasTag(CustomTagsEnum.Soul_Stone)) {
                    this.cannotDuplicate.add(c);
                }
            }
            if (this.cannotDuplicate.size() == this.p.hand.group.size()) {
                this.isDone = true;

                return;
            }

            if (this.p.hand.group.size() - this.cannotDuplicate.size() == 1) {
                for (AbstractCard c : this.p.hand.group) {
                    if (!c.hasTag(CustomTagsEnum.Soul_Stone)) {
                        c.setCostForTurn(0);


                        this.isDone = true;
                        return;
                    }
                }
            }

            this.p.hand.group.removeAll(this.cannotDuplicate);

            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, false, false, false, true);
                addToBot(new WaitAction(0.25F));
                tickDuration();
                return;
            }


            if (this.p.hand.group.size() == 1) {
                this.p.hand.getTopCard().setCostForTurn(0);
                returnCards();
                this.isDone = true;
            }

        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {

            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                c.setCostForTurn(0);
                AbstractDungeon.player.hand.addToTop(c);
            }

            returnCards();

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }


    private void returnCards() {
        for (AbstractCard c : this.cannotDuplicate) {
            this.p.hand.addToTop(c);
        }
        this.p.hand.refreshHandLayout();
    }

}


