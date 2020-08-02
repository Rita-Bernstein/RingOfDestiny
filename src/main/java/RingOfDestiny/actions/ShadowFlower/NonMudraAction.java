package RingOfDestiny.actions.ShadowFlower;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class NonMudraAction  extends AbstractGameAction {
    public NonMudraAction(){

    }


    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));

        tickDuration();

        if (this.isDone)
            for (AbstractCard c : DrawCardAction.drawnCards) {
                    c.setCostForTurn(0);
            }
    }
}


