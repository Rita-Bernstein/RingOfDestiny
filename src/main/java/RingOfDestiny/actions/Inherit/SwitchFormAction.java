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

public class SwitchFormAction extends AbstractGameAction {
    private boolean changeForFree;
    private boolean setForm;
    private boolean changeToDark;

    public SwitchFormAction(boolean changeForFree,boolean setForm,boolean changeToDark) {
        this.changeForFree = changeForFree;
        this.setForm = setForm;
        this.changeToDark = changeToDark;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public SwitchFormAction(boolean changeForFree,boolean changeToDark) {
        this(changeForFree,false,changeToDark);
    }

    public SwitchFormAction() {
        this(false,false,false);
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            EnergyPanelRenderPatches.PatchEnergyPanelField.canUseSubEnergy.set(AbstractDungeon.overlayMenu.energyPanel, true);
            boolean switchToDark;
            if(setForm){
                switchToDark = this.changeToDark;
            }else {
                switchToDark = !EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(AbstractDungeon.overlayMenu.energyPanel);
            }


            EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).switchForm(switchToDark,changeForFree);
        }
        tickDuration();
    }
}


