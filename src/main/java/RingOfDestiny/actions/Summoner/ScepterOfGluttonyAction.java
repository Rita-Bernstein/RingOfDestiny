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


public class ScepterOfGluttonyAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("ScepterOfGluttonyAction"));
    public static final String[] TEXT = uiStrings.TEXT;
    private AbstractPlayer p;
    private int amount;


    public ScepterOfGluttonyAction(int amount) {
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
    }

    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, true, false, false, true);
            addToBot(new WaitAction(0.25F));
            tickDuration();

            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {

            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                if (c.costForTurn > 1) {
                    c.costForTurn = 1;
                    c.isCostModifiedForTurn = true;
                }
                AbstractDungeon.player.hand.addToTop(c);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
    }
}


