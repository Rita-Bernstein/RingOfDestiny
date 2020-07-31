package RingOfDestiny.actions.unique;


import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;


public class HegemonyAction extends AbstractGameAction {

    public HegemonyAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ArrayList<AbstractCard> groupCopy = new ArrayList<AbstractCard>();
            AbstractCard card = null;

            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.cost > 0 && c.costForTurn > 0 && !c.freeToPlayOnce && c.type == AbstractCard.CardType.ATTACK) {
                    groupCopy.add(c);
                }
            }

            for (CardQueueItem i : AbstractDungeon.actionManager.cardQueue) {
                if (i.card != null) {
                    groupCopy.remove(i.card);
                }
            }


            if (!groupCopy.isEmpty()) {
                card = groupCopy.get(AbstractDungeon.miscRng.random(0, groupCopy.size() - 1));
            }

            if (card != null) {
                card.setCostForTurn(-99);
                card.superFlash();
            }
        }
        tickDuration();
    }
}




