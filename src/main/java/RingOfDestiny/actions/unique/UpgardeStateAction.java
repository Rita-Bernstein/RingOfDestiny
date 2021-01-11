package RingOfDestiny.actions.unique;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.*;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;

public class UpgardeStateAction extends AbstractGameAction {

    private static final float DURATION = 0.5F;
    private boolean gotBurned = false;


    public UpgardeStateAction(float setDur) {
        this.duration = setDur;

        this.actionType = ActionType.WAIT;


    }

    public void update() {
        if (this.duration == 0.5F) {

            if (AbstractDungeon.player.drawPile.size() > 0) {
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c instanceof Burn) {
                        c.upgrade();
                    }
                    if (c instanceof Wound) {
                        int i = AbstractDungeon.player.drawPile.group.indexOf(c);
                        AbstractDungeon.player.drawPile.group.set(i, new Burn());
                    }
                    if (c instanceof VoidCard) {
                        int i = AbstractDungeon.player.drawPile.group.indexOf(c);
                        AbstractDungeon.player.drawPile.group.set(i, new Wound());
                    }
                    if (c instanceof Slimed) {
                        int i = AbstractDungeon.player.drawPile.group.indexOf(c);
                        AbstractDungeon.player.drawPile.group.set(i, new Wound());
                    }
                    if (c instanceof Dazed) {
                        int i = AbstractDungeon.player.drawPile.group.indexOf(c);
                        AbstractDungeon.player.drawPile.group.set(i, new Wound());
                    }

                }
            }

            if (AbstractDungeon.player.discardPile.size() > 0) {
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c instanceof Burn) {
                        c.upgrade();
                    }
                    if (c instanceof Wound) {
                        int i = AbstractDungeon.player.discardPile.group.indexOf(c);
                        AbstractDungeon.player.discardPile.group.set(i, new Burn());
                    }
                    if (c instanceof VoidCard) {
                        int i = AbstractDungeon.player.discardPile.group.indexOf(c);
                        AbstractDungeon.player.discardPile.group.set(i, new Wound());
                    }
                    if (c instanceof Slimed) {
                        int i = AbstractDungeon.player.discardPile.group.indexOf(c);
                        AbstractDungeon.player.discardPile.group.set(i, new Wound());
                    }
                    if (c instanceof Dazed) {
                        int i = AbstractDungeon.player.discardPile.group.indexOf(c);
                        AbstractDungeon.player.discardPile.group.set(i, new Wound());
                    }

                }

            }

        }
        tickDuration();
    }
}