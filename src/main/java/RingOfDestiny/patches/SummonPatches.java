package RingOfDestiny.patches;


import RingOfDestiny.summon.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class SummonPatches {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method= SpirePatch.CLASS
    )
    public static class AbstractPlayerSummonField {
        public static SpireField<AbstractSummon> summon = new SpireField<>(() -> new Demon());
    }


    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "render"
    )
    public static class SummonRenderPatch {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<Void> Insert(AbstractPlayer _instance, SpriteBatch sb) {
            AbstractPlayerSummonField.summon.get(_instance).render(sb);


            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "update"
    )
    public static class SummonUpdatePatch {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<Void> Insert(AbstractPlayer _instance) {
            AbstractPlayerSummonField.summon.get(_instance).update();


            return SpireReturn.Continue();
        }
    }



}


