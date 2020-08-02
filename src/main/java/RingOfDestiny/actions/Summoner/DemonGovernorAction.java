package RingOfDestiny.actions.Summoner;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DemonGovernorAction extends AbstractGameAction {
    private float startingDuration;
    private int amount;

    public DemonGovernorAction(int amount) {
        this.target = AbstractDungeon.player;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
    }


    public void update() {
        if (this.duration == this.startingDuration) {
            int count = AbstractDungeon.player.hand.size();
            addToTop(new SummonAttackAction(this.amount * count));
            addToTop(new DiscardAction(this.target, this.target, count, true));

            this.isDone = true;
        }
    }
}


