package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AddDiamondAction extends AbstractGameAction {
    private int amount;
    private boolean isRelic;

    public AddDiamondAction(int amount) {
        this.amount = amount;
        this.isRelic = false;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public AddDiamondAction(int amount,boolean isRelic) {
        this.amount = amount;
        this.isRelic = isRelic;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            EnergyPanelRenderPatches.PatchEnergyPanelField.canUseDiamond.set(AbstractDungeon.overlayMenu.energyPanel,true);
            EnergyPanelRenderPatches.PatchEnergyPanelField.diamondManager.get(AbstractDungeon.overlayMenu.energyPanel).createDiamond(this.amount, this.isRelic);
        }
        tickDuration();
    }
}


