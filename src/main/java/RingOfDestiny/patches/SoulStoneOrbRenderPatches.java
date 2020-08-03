package RingOfDestiny.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

import static com.brashmonkey.spriter.Spriter.draw;


public class SoulStoneOrbRenderPatches {
    public static final Color soulStoneOrbRenderColor = Color.WHITE.cpy();
    public static final Texture soulStoneOrb = ImageMaster.loadImage("RingOfDestiny/img/cardui/Summoner/1024/card_lime_orb2.png");
    public static final TextureAtlas.AtlasRegion soulStoneAtlas = new TextureAtlas.AtlasRegion(soulStoneOrb, 0, 0, 246, 246);

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "renderCost"
    )
    public static class DiamondManagerEndOfTurnPatch {

        @SpireInsertPatch(rloc = 3, localvars = {"card"})
        public static SpireReturn<Void> Insert(SingleCardViewPopup _instance, SpriteBatch sb,
                                               AbstractCard card) {

            if (card.hasTag(CustomTagsEnum.Soul_Stone)) {
                renderHelper(sb, (float)Settings.WIDTH / 2.0F - 270.0F * Settings.scale, (float)Settings.HEIGHT / 2.0F + 380.0F * Settings.scale, soulStoneAtlas);

            }

            return SpireReturn.Continue();
        }
    }

    public static void renderHelper(SpriteBatch sb, float x, float y, TextureAtlas.AtlasRegion img) {
        if (img != null) {
            sb.draw(img, x + img.offsetX - (float)img.originalWidth / 2.0F, y + img.offsetY - (float)img.originalHeight / 2.0F, (float)img.originalWidth / 2.0F - img.offsetX, (float)img.originalHeight / 2.0F - img.offsetY, (float)img.packedWidth, (float)img.packedHeight, Settings.scale, Settings.scale, 0.0F);
        }
    }

}


