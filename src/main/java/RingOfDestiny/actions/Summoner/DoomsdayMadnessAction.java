package RingOfDestiny.actions.Summoner;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class DoomsdayMadnessAction extends AbstractGameAction {
    private boolean drawAgain = true;
    private int amount ;
    private int times ;

    public DoomsdayMadnessAction(int amount,int times){
        this.amount = amount;
        this.times = times;
        this.duration = 0.001F;
    }

    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));

        tickDuration();

        if (this.isDone){
            for (AbstractCard c : DrawCardAction.drawnCards) {
                if(c.costForTurn != 0)
                    this.drawAgain = false;

            }

            this.times--;

            if (this.drawAgain && this.times > 0)
                addToTop(new DrawCardAction(this.amount, new DoomsdayMadnessAction(1,this.times)));
        }

    }
}


