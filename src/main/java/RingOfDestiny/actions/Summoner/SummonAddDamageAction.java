package RingOfDestiny.actions.Summoner;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.summon.AbstractSummon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SummonAddDamageAction extends AbstractGameAction {
    private int amount;
    private AbstractSummon summon;

    public SummonAddDamageAction(AbstractSummon summon,int amount) {
        this.summon = summon;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public SummonAddDamageAction(AbstractSummon summon) {
        this(summon,1);
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            this.summon.damage += this.amount;
        }
        tickDuration();
    }
}


