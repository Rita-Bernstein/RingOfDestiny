package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UseDiamondAction extends AbstractGameAction {
    private int amount;

    public UseDiamondAction(int amount) {
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if(EnergyPanelRenderPatches.PatchEnergyPanelField.canUseDiamond.get(AbstractDungeon.overlayMenu.energyPanel)){
                System.out.println("玩家有氪金石UI");
                EnergyPanelRenderPatches.PatchEnergyPanelField.diamondManager.get(AbstractDungeon.overlayMenu.energyPanel).evokeDiamond(this.amount);
            }else {
                this.isDone = true;
                return;
            }
        }
        tickDuration();
    }
}


