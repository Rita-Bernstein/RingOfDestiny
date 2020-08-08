package RingOfDestiny.patches;

import RingOfDestiny.cards.AbstractInheritCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.lang.reflect.Method;

import static com.brashmonkey.spriter.Spriter.draw;


public class SubCostRenderPatches {
    public static int costCpy;
    public static boolean isCostModifiedCpy;
    public static final Texture lightSwitch = ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Inherit/changeBtn_light.png");
    public static final Texture darkSwitch = ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Inherit/changeBtn_dark.png");
    public static final Color SwitchRenderColor = Color.WHITE.cpy();
    public static final float hb_scale = 1.2f;

    public static final TextureAtlas.AtlasRegion subEnergyLargeOrb = new TextureAtlas.AtlasRegion(ImageMaster.loadImage("RingOfDestiny/img/cardui/Inherit/1024/card_lime_orb2.png"), 0, 0, 246, 246);

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = SpirePatch.CLASS
    )
    public static class SingleCardViewPopupField {
        public static SpireField<Hitbox> switchHitbox = new SpireField<>(() -> new Hitbox(96.0f * Settings.scale * hb_scale, 96.0f * Settings.scale * hb_scale));
    }


    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "update"
    )
    public static class updatePatch {
        @SpireInsertPatch(rloc = 0, localvars = {"card"})
        public static SpireReturn<Void> Insert(SingleCardViewPopup _instance,
                                               @ByRef(type = "cards.AbstractCard") Object[] card) {
            if ((card[0] instanceof AbstractInheritCard)) {
                AbstractInheritCard c = ((AbstractInheritCard) card[0]);
                Hitbox hb = SingleCardViewPopupField.switchHitbox.get(_instance);

                float cX = Settings.WIDTH / 2.0F - 0.0f * Settings.scale;
                float cY = Settings.HEIGHT / 2.0F - 370.0f * Settings.scale;

                hb.move(cX, cY);

                hb.update();

                if (InputHelper.justClickedLeft && hb.hovered) {
                    hb.clickStarted = true;
                }

                if (hb.clicked) {
                    hb.clicked = false;
                    c.initializeForm(!c.isDark);

//                    try {
//                        Method methodLoad = SingleCardViewPopup.class.getDeclaredMethod("loadPortraitImg");
//                        methodLoad.setAccessible(true);
//                        methodLoad.invoke(_instance);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                }

            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "render"
    )
    public static class SubCostRenderPatch {

        @SpireInsertPatch(rloc = 87, localvars = {"card"})
        public static SpireReturn<Void> Insert(SingleCardViewPopup _instance, SpriteBatch sb,
                                               @ByRef(type = "cards.AbstractCard") Object[] card) {
            if ((card[0] instanceof AbstractInheritCard)) {
                AbstractInheritCard c = ((AbstractInheritCard) card[0]);
                Hitbox hb = SingleCardViewPopupField.switchHitbox.get(_instance);
                sb.setColor(SwitchRenderColor);
                if (c.isDark) {
                    sb.draw(lightSwitch, hb.cX - 45.0F, hb.cY - 54.0F, 45.0F, 54.0F, 90.0F, 108.0F, hb_scale * Settings.scale, hb_scale * Settings.scale, 0.0F, 0, 0, 90, 108, false, false);
                } else {
                    sb.draw(darkSwitch, hb.cX - 45.0F, hb.cY - 54.0F, 45.0F, 54.0F, 90.0F, 108.0F, hb_scale * Settings.scale, hb_scale * Settings.scale, 0.0F, 0, 0, 90, 108, false, false);
                }


                SingleCardViewPopupField.switchHitbox.get(_instance).render(sb);
            }


            return SpireReturn.Continue();
        }
    }

//    @SpirePatch(
//            clz = SingleCardViewPopup.class,
//            method = "renderCost"
//    )
//    public static class SubCostRenderCostPatchPre {
//
//        @SpireInsertPatch(rloc = 0, localvars = {"card"})
//        public static SpireReturn<Void> Insert(SingleCardViewPopup _instance, SpriteBatch sb,
//                                               @ByRef(type = "cards.AbstractCard") Object[] card) {
//            if ((card[0] instanceof AbstractInheritCard)) {
//                AbstractInheritCard c = ((AbstractInheritCard) card[0]);
//                if (c.isDark) {
//                    costCpy = c.cost;
//                    isCostModifiedCpy = c.isCostModified;
//                    c.cost = c.subCost;
//                    c.isCostModified = c.isSubCostModified;
//                }
//            }
//
//
//            return SpireReturn.Continue();
//        }
//    }

//
//    @SpirePatch(
//            clz = SingleCardViewPopup.class,
//            method = "renderCost"
//    )
//    public static class SubCostRenderPatchPost {
//
//        @SpireInsertPatch(rloc = 65, localvars = {"card"})
//        public static SpireReturn<Void> Insert(SingleCardViewPopup _instance, SpriteBatch sb,
//                                               @ByRef(type = "cards.AbstractCard") Object[] card) {
//            if ((card[0] instanceof AbstractInheritCard)) {
//                AbstractInheritCard c = ((AbstractInheritCard) card[0]);
//
//                if (c.isDark) {
//                    c.cost = costCpy;
//                    c.isCostModified = isCostModifiedCpy;
//                }
//            }
//
//
//            return SpireReturn.Continue();
//        }
//    }


    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "renderPortrait"
    )
    public static class loadPortraitImgPatchA {

        @SpireInsertPatch(rloc = 0, localvars = {"card"})
        public static SpireReturn<Void> Insert(SingleCardViewPopup _instance, SpriteBatch sb,
                                               @ByRef(type = "com.megacrit.cardcrawl.cards.AbstractCard") Object[] card) {
            if ((card[0] instanceof AbstractInheritCard)) {
                AbstractInheritCard c = ((AbstractInheritCard) card[0]);
                if (c.isDark) {
                    sb.draw(c.getLargeCardIMG(c.imgSubPath), Settings.WIDTH / 2.0F - 250.0F, Settings.HEIGHT / 2.0F - 190.0F + 136.0F * Settings.scale, 250.0F, 190.0F, 500.0F, 380.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 500, 380, false, false);
                } else {
                    sb.draw(c.getLargeCardIMG(c.imgPath), Settings.WIDTH / 2.0F - 250.0F, Settings.HEIGHT / 2.0F - 190.0F + 136.0F * Settings.scale, 250.0F, 190.0F, 500.0F, 380.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 500, 380, false, false);
                }


                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "renderCost"
    )
    public static class renderCostPatch {

        @SpireInsertPatch(rloc = 0, localvars = {"card"})
        public static SpireReturn<Void> Insert(SingleCardViewPopup _instance, SpriteBatch sb,
                                               @ByRef(type = "com.megacrit.cardcrawl.cards.AbstractCard") Object[] card) {
            if ((card[0] instanceof AbstractInheritCard)) {
                AbstractInheritCard c = ((AbstractInheritCard) card[0]);
                if (c.isDark) {
                    if (c.isLocked || !c.isSeen) {
                        return SpireReturn.Return(null);
                    }

                    if (c.subCost > -2) {
                        renderHelper(sb, Settings.WIDTH / 2.0F - 270.0F * Settings.scale, Settings.HEIGHT / 2.0F + 380.0F * Settings.scale, subEnergyLargeOrb);
                    }


                    Color color = null;
                    if (c.isSubCostModified) {
                        color = Settings.GREEN_TEXT_COLOR;
                    } else {
                        color = Settings.CREAM_COLOR;
                    }
                    switch (c.subCost) {
                        case -2:
                            return SpireReturn.Return(null);
                        case -1:
                            FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, "X", 666.0F * Settings.scale, Settings.HEIGHT / 2.0F + 404.0F * Settings.scale, color);
                        case 1:
                            FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, Integer.toString(c.subCost), 674.0F * Settings.scale, Settings.HEIGHT / 2.0F + 404.0F * Settings.scale, color);
                    }

                    FontHelper.renderFont(sb, FontHelper.SCP_cardEnergyFont, Integer.toString(c.subCost), 668.0F * Settings.scale, Settings.HEIGHT / 2.0F + 404.0F * Settings.scale, color);


                    return SpireReturn.Return(null);
                } else {

                    for (int i = 0; i < c.subGain; i++) {
                        sb.draw(c.subGainOrb, Settings.WIDTH / 2.0F - 270.0F * Settings.scale - 7.0F, Settings.HEIGHT / 2.0F + (250.0F - i * 70.0f) * Settings.scale - 7.0F, 7.0F, 7.0F, 14.0F, 14.0F, Settings.scale * 3.2f, Settings.scale * 3.2f, 0.0f, 0, 0, 14, 14, false, false);
                    }


                    return SpireReturn.Continue();
                }
            }
            return SpireReturn.Continue();
        }
    }


    public static void renderHelper(SpriteBatch sb, float x, float y, TextureAtlas.AtlasRegion img) {
        if (img == null) {
            return;
        }

        sb.draw(img, x + img.offsetX - img.originalWidth / 2.0F, y + img.offsetY - img.originalHeight / 2.0F, img.originalWidth / 2.0F - img.offsetX, img.originalHeight / 2.0F - img.offsetY, img.packedWidth, img.packedHeight, Settings.scale, Settings.scale, 0.0F);
    }


}


