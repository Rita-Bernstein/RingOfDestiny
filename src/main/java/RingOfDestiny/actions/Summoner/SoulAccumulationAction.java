package RingOfDestiny.actions.Summoner;

import RingOfDestiny.patches.CustomTagsEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class SoulAccumulationAction extends AbstractGameAction {

    public SoulAccumulationAction(){
        this.duration = 0.001F;
    }

    public void update() {
        AbstractDungeon.actionManager.addToTop(new WaitAction(0.4F));
        tickDuration();

        if (this.isDone)
            for (AbstractCard c : DrawCardAction.drawnCards) {
                if (c.hasTag(CustomTagsEnum.Soul_Stone)) {
                    addToBot(new AddSoulStoneAction(1));
                    break;
                }
            }

        tickDuration();
    }
}


