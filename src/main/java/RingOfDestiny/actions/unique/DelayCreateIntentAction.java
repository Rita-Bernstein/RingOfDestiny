package RingOfDestiny.actions.unique;

import RingOfDestiny.monster.IdeologyCorridor.SpiderChildG;
import RingOfDestiny.powers.Monster.IdeologyCorridor.VenomPower;
import RingOfDestiny.vfx.BackgroundBoardRender;
import RingOfDestiny.vfx.BackgroundRender;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DelayCreateIntentAction extends AbstractGameAction {

    private AbstractMonster m;


    public DelayCreateIntentAction(AbstractMonster m) {
        this.m = m;
    }

    public void update() {
        m.createIntent();
        this.isDone = true;
    }
}