package RingOfDestiny.actions.MagicBullet;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;


public class ExhaustAllNonSkillAction extends AbstractGameAction {
    private float startingDuration;

    public ExhaustAllNonSkillAction() {
        this.actionType = ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type != AbstractCard.CardType.SKILL) {
                    addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
                }
            }
            this.isDone = true;


            if (AbstractDungeon.player.exhaustPile.size() >= 20)
                UnlockTracker.unlockAchievement("THE_PACT");
        }
    }
}


