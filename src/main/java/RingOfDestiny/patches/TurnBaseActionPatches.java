package RingOfDestiny.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
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

}


