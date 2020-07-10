package RingOfDestiny.actions.ShadowFlower;

import RingOfDestiny.RingOfDestiny;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

public class WaterReleaseAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("WaterReleaseAction"));
    public static final String[] TEXT = uiStrings.TEXT;
    private int amount;

    public WaterReleaseAction(AbstractCreature source, int amount) {
        setValues(AbstractDungeon.player, source, -1);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.amount = amount;
    }


    public void update() {
        if (this.duration == 0.5F) {
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, true, true);

            addToBot(new WaitAction(0.25F));
            tickDuration();

            return;
        }

        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {


            if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                addToTop(new GainEnergyAction(AbstractDungeon.handCardSelectScreen.selectedCards.group.size()));

                for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                    AbstractDungeon.player.hand.moveToDiscardPile(c);
                    GameActionManager.incrementDiscard(false);
                    c.triggerOnManualDiscard();
                }
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }

        tickDuration();
    }
}


