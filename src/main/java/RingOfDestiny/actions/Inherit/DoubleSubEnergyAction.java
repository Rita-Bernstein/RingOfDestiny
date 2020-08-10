package RingOfDestiny.actions.Inherit;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DoubleSubEnergyAction extends AbstractGameAction {


    public DoubleSubEnergyAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            EnergyPanelRenderPatches.PatchEnergyPanelField.canUseSubEnergy.set(AbstractDungeon.overlayMenu.energyPanel, true);
            EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).addSubEnergy(
                    EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).totalCount,
                    false);
        }
        tickDuration();
    }
}


