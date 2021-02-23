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

public class ApplyVenomPowerToMinionAction extends AbstractGameAction {

    private AbstractCreature source;


    public ApplyVenomPowerToMinionAction( AbstractCreature source) {
        this.source = source;
    }

    public void update() {
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (!m.isDying && m instanceof SpiderChildG) {
                addToBot(new ApplyPowerAction(m,m, new VenomPower(m)));
            }
        }
        this.isDone = true;
    }
}