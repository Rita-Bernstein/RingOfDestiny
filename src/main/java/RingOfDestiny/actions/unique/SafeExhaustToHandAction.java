package RingOfDestiny.actions.unique;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SafeExhaustToHandAction extends AbstractGameAction {
    private AbstractCard card;

    public SafeExhaustToHandAction(AbstractCard card) {
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.card = card;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.exhaustPile.contains(this.card)) {
                if (AbstractDungeon.player.hand.size() < 10) {
                    AbstractDungeon.player.hand.addToHand(this.card);
                } else {
                    AbstractDungeon.player.discardPile.addToBottom(this.card);
                }
                this.card.stopGlowing();
                this.card.unhover();
                this.card.unfadeOut();
                this.card.setAngle(0.0F, true);
                this.card.lighten(false);
                this.card.drawScale = 0.12F;
                this.card.targetDrawScale = 0.75F;
                this.card.applyPowers();
                AbstractDungeon.player.exhaustPile.removeCard(this.card);
            }

            AbstractDungeon.player.hand.refreshHandLayout();
            AbstractDungeon.player.hand.glowCheck();
        }

        tickDuration();
        this.isDone = true;
    }
}


