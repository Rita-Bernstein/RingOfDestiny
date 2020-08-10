package RingOfDestiny.actions.Inherit;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class PlayTopExhasutCardAction extends AbstractGameAction {
    private int amount;

    public PlayTopExhasutCardAction(int amount) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.amount = amount;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.discardPile.size() == 0) {
                this.isDone = true;

                return;
            }

            if (!AbstractDungeon.player.discardPile.isEmpty()) {
                ArrayList<AbstractCard> tmp = new ArrayList<>();
                for (int i = 0; i < this.amount; i++)
                    tmp.add(AbstractDungeon.player.exhaustPile.getTopCard().makeStatEquivalentCopy());

                for (AbstractCard c : tmp) {
                    c.purgeOnUse = true;
                    addToBot(new NewQueueCardAction(c, true, false, true));
                }

                AbstractDungeon.player.hand.refreshHandLayout();

            }
            this.isDone = true;
        }
    }
}


