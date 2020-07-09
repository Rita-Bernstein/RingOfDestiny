package RingOfDestiny.actions.ShadowFlower;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WoodReleaseAction extends AbstractGameAction {
    private float startingDuration;
    private int block;

    public WoodReleaseAction(int block) {
        this.target = AbstractDungeon.player;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = Settings.ACTION_DUR_FAST;
        this.block = block;
    }


    public void update() {
        if (this.duration == this.startingDuration) {
            int count = AbstractDungeon.player.hand.size();
            if (count != 0) {
                for(int i =0; i< count;i++){
                    addToTop(new GainBlockAction(this.target,this.target, block));
                }
                addToTop(new DiscardAction(this.target, this.target, count, true));
            }

            this.isDone = true;
        }
    }
}


