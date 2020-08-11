package RingOfDestiny.patches;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class AbstractRingPowerPatches {


    @SpirePatch(
            clz = AbstractPower.class,
            method = SpirePatch.CLASS
    )
    public static class PatchEnergyPanelField {
        public static SpireField<TextureAtlas> ringAtlas = new SpireField<>(() -> null);
    }

    @SpirePatch(
            clz = AbstractPower.class,
            method = "initialize"
    )
    public static class initializePatch {
        @SpirePostfixPatch
        public static SpireReturn<Void> Postfix(AbstractPower _instance) {
            PatchEnergyPanelField.ringAtlas.set(_instance, new TextureAtlas(Gdx.files.internal("RingOfDestiny/powers/ringPowers.atlas")));
            return SpireReturn.Continue();
        }
    }
}


