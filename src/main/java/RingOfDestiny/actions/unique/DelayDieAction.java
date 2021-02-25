package RingOfDestiny.actions.unique;

import RingOfDestiny.vfx.BackgroundBoardRender;
import RingOfDestiny.vfx.BackgroundRender;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DelayDieAction extends AbstractGameAction {
    private AbstractMonster m;

    public DelayDieAction(AbstractMonster m) {
        this.m = m;
    }

    public void update() {
        this.m.die(true);

        this.isDone = true;
    }
}