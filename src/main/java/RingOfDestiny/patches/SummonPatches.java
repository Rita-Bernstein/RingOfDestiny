package RingOfDestiny.patches;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.character.MagicBullet;
import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.*;
import RingOfDestiny.soulStone.SoulStone;
import RingOfDestiny.summon.AbstractSummon;
import RingOfDestiny.summon.Demon;
import RingOfDestiny.summon.NullSummon;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.lang.reflect.Constructor;

import static basemod.BaseMod.getModdedCharacters;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;


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


