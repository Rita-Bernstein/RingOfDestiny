package RingOfDestiny.patches;

import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.TempMusic;

@SpirePatch(
        clz = TempMusic.class,
        method = "getSong")
public class TempMusicPatch {

    @SpirePostfixPatch
    public static SpireReturn<Music> Prefix(TempMusic __instance, String key) {
        if(key.equals("RingOfDestiny:music01")) {
            return SpireReturn.Return(MainMusic.newMusic("RingOfDestiny/audio/music/01 Dark Gravitation.ogg"));
        }
        if(key.equals("RingOfDestiny:music02")) {
            return SpireReturn.Return(MainMusic.newMusic("RingOfDestiny/audio/music/02 Testament of Nests.ogg"));
        }
        if(key.equals("RingOfDestiny:music15")) {
            return SpireReturn.Return(MainMusic.newMusic("RingOfDestiny/audio/music/15 Going to R&D (Boss Interim Demo).ogg"));
        }
        if(key.equals("RingOfDestiny:music16")) {
            return SpireReturn.Return(MainMusic.newMusic("RingOfDestiny/audio/music/16 Duel R&D (Boss Stage 1).ogg"));
        }
        if(key.equals("RingOfDestiny:music17")) {
            return SpireReturn.Return(MainMusic.newMusic("RingOfDestiny/audio/music/17 Um... (After Boss Stage 1 Demo).ogg"));
        }
        if(key.equals("RingOfDestiny:music18")) {
            return SpireReturn.Return(MainMusic.newMusic("RingOfDestiny/audio/music/18 Showdown R&D (Boss Stage 2).ogg"));
        }
        if(key.equals("RingOfDestiny:music19")) {
            return SpireReturn.Return(MainMusic.newMusic("RingOfDestiny/audio/music/19 Pre Bon (Ending 1).ogg"));
        }
        if(key.equals("RingOfDestiny:music20")) {
            return SpireReturn.Return(MainMusic.newMusic("RingOfDestiny/audio/music/20.Praise for Rugal (Rugal Appearance Demo).ogg"));
        }
        if(key.equals("RingOfDestiny:music21")) {
            return SpireReturn.Return(MainMusic.newMusic("RingOfDestiny/audio/music/21 Second Coming... (Guitar, Omega and Rugal KOF Sky Stage Ver.).ogg"));
        }
        if(key.equals("RingOfDestiny:music22")) {
            return SpireReturn.Return(MainMusic.newMusic("RingOfDestiny/audio/music/22.Rugal Elimination (Rugal Elimination Demo).ogg"));
        }

        return SpireReturn.Continue();
    }

}







