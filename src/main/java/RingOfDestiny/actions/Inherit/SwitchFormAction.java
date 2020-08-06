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
    private boolean changToDark;

    public SwitchFormAction(boolean changeForFree,boolean setForm,boolean changToDark) {
        this.changeForFree = changeForFree;
        this.setForm = setForm;
        this.changToDark = changToDark;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public SwitchFormAction() {
        this(false,false,false);
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            EnergyPanelRenderPatches.PatchEnergyPanelField.canUseSubEnergy.set(AbstractDungeon.overlayMenu.energyPanel, true);
            boolean switchToDark;
            if(setForm){
                switchToDark = this.changToDark;
            }else {
                switchToDark = !EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(AbstractDungeon.overlayMenu.energyPanel);
            }


            EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).switchForm(switchToDark,changeForFree);

            for(AbstractCard card : AbstractDungeon.player.hand.group){
                if(card instanceof AbstractInheritCard){
                    ((AbstractInheritCard)card).initializeForm(switchToDark);
                }
            }

            for(AbstractCard card : AbstractDungeon.player.discardPile.group){
                if(card instanceof AbstractInheritCard){
                    ((AbstractInheritCard)card).initializeForm(switchToDark);
                }
            }

            for(AbstractCard card : AbstractDungeon.player.drawPile.group){
                if(card instanceof AbstractInheritCard){
                    ((AbstractInheritCard)card).initializeForm(switchToDark);
                }
            }

            for(AbstractCard card : AbstractDungeon.player.exhaustPile.group){
                if(card instanceof AbstractInheritCard){
                    ((AbstractInheritCard)card).initializeForm(switchToDark);
                }
            }
        }
        tickDuration();
    }
}


