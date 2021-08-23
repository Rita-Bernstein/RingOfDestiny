package RingOfDestiny.patches;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.skinCharacters.AbstractSkinCharacter;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.VictoryScreen;


public class VictoryScreenPatches {
    @SpirePatch
            (clz = VictoryScreen.class,
                    method = "updateAscensionAndBetaArtProgress"
            )
    public static class ReskinUnlockPatch {

        @SpirePrefixPatch
        public static void Prefix(VictoryScreen _instance) {
            if (!Settings.seedSet && !Settings.isTrial) {
                for (AbstractSkinCharacter c : CharacterSelectScreenPatches.characters) {
                    c.checkUnlock();
                }
                RingOfDestiny.saveSkins();
            }
            SpireReturn.Continue();
        }
    }
}
