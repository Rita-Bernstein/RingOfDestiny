package RingOfDestiny.helpers;


import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SubEnergyModifier extends AbstractCardModifier implements AlternateCardCostModifier {

    public SubEnergyModifier() {
    }


    @Override
    public int getAlternateResource(AbstractCard card) {
        return EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).totalCount;
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        if (card instanceof AbstractInheritCard) {
            return ((AbstractInheritCard) card).isDark;
        } else {
            return false;
        }
    }

    @Override
    public int spendAlternateCost(AbstractCard card, int costToSpend) {
        if (card instanceof AbstractInheritCard) {
            int resource = EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).totalCount;
            if (resource > costToSpend) {
                AbstractDungeon.actionManager.addToBottom(new UseSubEnergyAction(costToSpend));
                costToSpend = 0;
            } else {
                AbstractDungeon.actionManager.addToBottom(new UseSubEnergyAction(resource));
                costToSpend -= resource;
            }
        }
        return costToSpend;
    }



    @Override
    public AbstractCardModifier makeCopy() {
        return new SubEnergyModifier();
    }
}