package RingOfDestiny.patches;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.character.MagicBullet;
import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.EmptyDiamondSlot;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static basemod.BaseMod.getModdedCharacters;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;


public class EnergyPanelRenderPatches {

    @SpirePatch(
            clz = EnergyPanel.class,
            method=SpirePatch.CLASS
    )
    public static class PatchEnergyPanelField {
        public static SpireField<AbstractDiamond[]> diamonds  = new SpireField<>(() -> new  AbstractDiamond[]{
                new EmptyDiamondSlot(),
                new EmptyDiamondSlot(),
                new EmptyDiamondSlot(),
                new EmptyDiamondSlot(),
                new EmptyDiamondSlot(),
                new EmptyDiamondSlot(),
                new EmptyDiamondSlot(),
                new EmptyDiamondSlot(),
                new EmptyDiamondSlot()});
    }

    @SpirePatch(
            clz = EnergyPanel.class,
            method = "render"
    )
    public static class ExtraEnergyPanelRenderPatch {
        @SpireInsertPatch(rloc = 3)
        public static SpireReturn<Void> Insert(EnergyPanel _instance, SpriteBatch sb) {
            AbstractDiamond[] di = PatchEnergyPanelField.diamonds.get(_instance);
            for(AbstractDiamond diamond : di){
                diamond.render(sb);
            }

            return SpireReturn.Continue();
        }
    }

}


