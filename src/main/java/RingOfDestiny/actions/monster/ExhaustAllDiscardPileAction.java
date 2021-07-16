package RingOfDestiny.actions.monster;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;


public class ExhaustAllDiscardPileAction extends AbstractGameAction {
    private float startingDuration;

    public ExhaustAllDiscardPileAction() {
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.discardPile));
            }
            this.isDone = true;

            if (AbstractDungeon.player.exhaustPile.size() >= 20)
                UnlockTracker.unlockAchievement("THE_PACT");
        }
    }
}


