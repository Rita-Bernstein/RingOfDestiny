package RingOfDestiny.actions.Inherit;

import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HandChangeSubCostForTurnAction extends AbstractGameAction {
    private int minCost;
    private int maxCost;

    public HandChangeSubCostForTurnAction(int minCost, int maxCost) {
        this.maxCost = maxCost;
        this.minCost = minCost;
        this.duration = Settings.ACTION_DUR_FAST;

    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            for (AbstractCard card : AbstractDungeon.player.hand.group) {
                if (card instanceof AbstractInheritCard) {
                    AbstractInheritCard c = (AbstractInheritCard) card;
                    int amt = AbstractDungeon.cardRandomRng.random(this.minCost, this.maxCost);
                    c.setSubCostForTurn(amt);
                }
            }
        }
        tickDuration();
    }
}


