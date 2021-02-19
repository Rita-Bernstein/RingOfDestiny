package RingOfDestiny.actions.unique;

import RingOfDestiny.vfx.BackgroundBoardRender;
import RingOfDestiny.vfx.BackgroundRender;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ClearStageAction extends AbstractGameAction {

    public ClearStageAction() {

    }

    public void update() {

        for (AbstractGameEffect e : AbstractDungeon.effectList) {
            if (e instanceof AbstractRingEffect)
                ((AbstractRingEffect) e).forceDone();
        }


        this.isDone = true;
    }
}