package RingOfDestiny.actions.Inherit;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class UseSubEnergyAction extends AbstractGameAction {
    private int amount;

    public UseSubEnergyAction(int amount) {
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (EnergyPanelRenderPatches.PatchEnergyPanelField.canUseSubEnergy.get(AbstractDungeon.overlayMenu.energyPanel)) {
                EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).useSubEnergy(this.amount);
            }
        }
        tickDuration();
    }
}


