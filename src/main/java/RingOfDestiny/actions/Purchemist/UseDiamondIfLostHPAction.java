package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UseDiamondIfLostHPAction extends AbstractGameAction {
    private int amount;
    private int hpForLost;

    public UseDiamondIfLostHPAction(int amount,int hpForLost) {
        this.amount = amount;
        this.hpForLost = hpForLost;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if(!EnergyPanelRenderPatches.PatchEnergyPanelField.diamondManager.get(AbstractDungeon.overlayMenu.energyPanel).enoughDiamond(this.amount)){
                addToBot(new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,this.hpForLost));
            }else {
                addToBot(new UseDiamondAction(this.amount));
            }
        }
        tickDuration();
    }
}


