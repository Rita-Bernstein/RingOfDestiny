package RingOfDestiny.patches;

import RingOfDestiny.ui.RingLoginBackground;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;

public class TitlePatch {
    @SpirePatch(
            clz = MainMenuScreen.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = { boolean.class}
    )
    public static class useCardPatch {
        @SpirePostfixPatch
        public static SpireReturn<Void> Insert(MainMenuScreen _instance) {
            _instance.bg = new RingLoginBackground();
            return SpireReturn.Continue();
        }
    }
}
