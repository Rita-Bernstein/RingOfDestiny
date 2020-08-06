package RingOfDestiny.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;


public class TurnBaseActionPatches {


    @SpirePatch(
            clz = AbstractPlayer.class,
            method= "applyStartOfTurnOrbs"
    )
    public static class DiamondManagerStartOfTurnPatch {
        @SpirePostfixPatch
        public static SpireReturn<Void> Postfix(AbstractPlayer _instance) {
            if(EnergyPanelRenderPatches.PatchEnergyPanelField.canUseDiamond.get(AbstractDungeon.overlayMenu.energyPanel)){
                EnergyPanelRenderPatches.PatchEnergyPanelField.diamondManager.get(AbstractDungeon.overlayMenu.energyPanel).onStartOfTurn();
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = GameActionManager.class,
            method= "callEndOfTurnActions"
    )
    public static class DiamondManagerEndOfTurnPatch {
        @SpireInsertPatch(rloc = 3)
        public static SpireReturn<Void> Insert(GameActionManager _instance) {
            if(EnergyPanelRenderPatches.PatchEnergyPanelField.canUseDiamond.get(AbstractDungeon.overlayMenu.energyPanel)){
                EnergyPanelRenderPatches.PatchEnergyPanelField.diamondManager.get(AbstractDungeon.overlayMenu.energyPanel).onEndOfTurn();
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method= "applyStartOfCombatPreDrawLogic"
    )
    public static class DiamondManagerApplyStartOfCombatPreDrawPatch {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<Void> Insert(AbstractPlayer _instance) {
            if(EnergyPanelRenderPatches.PatchEnergyPanelField.canUseDiamond.get(AbstractDungeon.overlayMenu.energyPanel)){
                EnergyPanelRenderPatches.PatchEnergyPanelField.diamondManager.get(AbstractDungeon.overlayMenu.energyPanel).reset();
            }

            if(EnergyPanelRenderPatches.PatchEnergyPanelField.canUseSubEnergy.get(AbstractDungeon.overlayMenu.energyPanel)){
                EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).reset();
            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = AbstractDungeon.class,
            method= "onModifyPower"
    )
    public static class DiamondManagerOnModifyPowerPatch {
        @SpireInsertPatch(rloc = 2)
        public static SpireReturn<Void> Insert() {
            if(EnergyPanelRenderPatches.PatchEnergyPanelField.canUseDiamond.get(AbstractDungeon.overlayMenu.energyPanel)){
                EnergyPanelRenderPatches.PatchEnergyPanelField.diamondManager.get(AbstractDungeon.overlayMenu.energyPanel).updateDescription();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = RemoveSpecificPowerAction.class,
            method= "update"
    )
    public static class DiamondManagerRemoveSpecificPowerActionPatch {
        @SpireInsertPatch(rloc = 25)
        public static SpireReturn<Void> Insert(RemoveSpecificPowerAction _instance) {
            if(EnergyPanelRenderPatches.PatchEnergyPanelField.canUseDiamond.get(AbstractDungeon.overlayMenu.energyPanel)){
                EnergyPanelRenderPatches.PatchEnergyPanelField.diamondManager.get(AbstractDungeon.overlayMenu.energyPanel).updateDescription();
            }
            return SpireReturn.Continue();
        }
    }
}


