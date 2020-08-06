package RingOfDestiny.actions.Inherit;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AddSubEnergyAction extends AbstractGameAction {
    private int amount;
    private boolean isRelic ;

    public AddSubEnergyAction(int amount,boolean isRelic) {
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_FAST;
        this.isRelic = isRelic;
    }

    public AddSubEnergyAction(int amount) {
        this(amount,false);
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            EnergyPanelRenderPatches.PatchEnergyPanelField.canUseSubEnergy.set(AbstractDungeon.overlayMenu.energyPanel,true);
            EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).addSubEnergy(this.amount,this.isRelic);
        }
        tickDuration();
    }
}


