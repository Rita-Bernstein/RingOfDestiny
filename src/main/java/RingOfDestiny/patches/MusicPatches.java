package RingOfDestiny.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.audio.MainMusic;


public class MusicPatches {
    @SpirePatch(
            clz = MainMusic.class,
            method = "newMusic"
    )
    public static class MainMusicPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(String path) {

            return SpireReturn.Continue();
        }
    }
}


