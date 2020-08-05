package RingOfDestiny.actions.Summoner;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class PowerOfArroganceAction extends AbstractGameAction {
    private boolean willAttack = false;
    private int times ;
    private boolean upgraded ;

    public PowerOfArroganceAction(boolean upgraded){
        this.times = 0;
        this.upgraded = upgraded;
        this.duration = 0.001F;
    }

    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));

        tickDuration();

        if (this.isDone){
            for (AbstractCard c : DrawCardAction.drawnCards) {

                if(c.type == AbstractCard.CardType.SKILL || (this.upgraded && c.type == AbstractCard.CardType.ATTACK))
                    this.willAttack = true;
                    this.times ++;
            }

            if (this.willAttack && this.times > 0)
                addToTop(new SummonAttackAction(this.times));
        }

    }
}


