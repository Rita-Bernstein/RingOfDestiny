package RingOfDestiny.actions.ShadowFlower;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EarthReleaseAction
        extends AbstractGameAction {
    public EarthReleaseAction(AbstractCreature source) {
        this.source = source;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    addToTop(new DiscardSpecificCardAction(c));
                }
            }
            this.isDone = true;
        }
    }
}


