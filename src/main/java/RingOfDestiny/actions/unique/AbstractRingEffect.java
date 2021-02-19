package RingOfDestiny.actions.unique;

import RingOfDestiny.vfx.BackgroundBoardRender;
import RingOfDestiny.vfx.BackgroundRender;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public abstract class AbstractRingEffect extends AbstractGameEffect {


    public AbstractRingEffect() {
    }

    protected void forceDone() {
        this.isDone = true;
    }

}