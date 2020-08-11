package RingOfDestiny.patches;


import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractInheritCard;
import basemod.patches.com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar.ColorTabBarFix;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar;
import javassist.CtBehavior;


public class DarkFormLibraryPatches {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("DarkFormLibraryPatches"));
    private static Hitbox DarkSwitchHb = new Hitbox(250.0F * Settings.scale, 48.0F * Settings.scale);
    public static boolean isViewingDark = false;


    @SpirePatch(
            clz = ColorTabBar.class,
            method = "update"
    )
    public static class ViewUpgradeButton {
        @SpirePostfixPatch
        public static void Postfix(ColorTabBar _instance, float y) {
            if (ColorTabBarFix.Fields.getModTab() != null && (ColorTabBarFix.Fields.getModTab()).color == CardColorEnum.Inherit_LIME && _instance.curTab == ColorTabBarFix.Enums.MOD) {
                DarkSwitchHb.move(1120.0F * Settings.scale, y);
                DarkSwitchHb.update();

                if (DarkSwitchHb.justHovered) {
                    CardCrawlGame.sound.playA("UI_HOVER", -0.3F);
                }

                if (DarkSwitchHb.hovered && InputHelper.justClickedLeft) {
                    DarkSwitchHb.clickStarted = true;
                }

                if (DarkSwitchHb.clicked || (DarkSwitchHb.hovered && CInputActionSet.select.isJustPressed())) {
                    DarkSwitchHb.clicked = false;
                    CardCrawlGame.sound.playA("UI_CLICK_1", -0.2F);
                    DarkFormLibraryPatches.isViewingDark = !DarkFormLibraryPatches.isViewingDark;
                }
            }
        }
    }


    @SpirePatch(
            clz = ColorTabBar.class,
            method = "render"
    )
    public static class renderViewUpgradeButton {
        public static void Postfix(ColorTabBar _instance, SpriteBatch sb, float y) {
            if (ColorTabBarFix.Fields.getModTab() != null && (ColorTabBarFix.Fields.getModTab()).color == CardColorEnum.Inherit_LIME) {
                DarkSwitchHb.render(sb);
            }
        }
    }


    @SpirePatch(
            clz = ColorTabBar.class,
            method = "renderViewUpgrade"
    )
    public static class renderViewUpgradeButton2 {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(ColorTabBar _instance, SpriteBatch sb, float y) {
            if (ColorTabBarFix.Fields.getModTab() != null && (ColorTabBarFix.Fields.getModTab()).color == CardColorEnum.Inherit_LIME) {
                Color c = Settings.CREAM_COLOR;
                if (DarkSwitchHb.hovered) {
                    c = Settings.GOLD_COLOR;
                }

                String desc = uiStrings.TEXT[0];
                Texture img = ImageMaster.COLOR_TAB_BOX_UNTICKED;
                if (DarkFormLibraryPatches.isViewingDark) {
                    img = ImageMaster.COLOR_TAB_BOX_TICKED;
                    desc = uiStrings.TEXT[1];
                }

                FontHelper.renderFontRightAligned(sb, FontHelper.topPanelInfoFont, desc, 1204.0F * Settings.scale, y, c);

                sb.setColor(c);
                sb.draw(img, 1190.0F * Settings.scale - FontHelper.getSmartWidth(FontHelper.topPanelInfoFont, desc, 9999.0F, 0.0F) - 24.0F, y - 24.0F, 24.0F, 24.0F, 48.0F, 48.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 48, 48, false, false);

            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderInLibrary"
    )
    public static class RenderInLibraryUpgrade {
        @SpireInsertPatch(rloc = 6, localvars = {"copy"})
        public static SpireReturn<Void> Insert(AbstractCard _instance,
                                               SpriteBatch sb, AbstractCard copy) {
            if (copy instanceof AbstractInheritCard) {
                if (DarkFormLibraryPatches.isViewingDark != ((AbstractInheritCard) copy).isDark) {
                    ((AbstractInheritCard) copy).initializeForm(DarkFormLibraryPatches.isViewingDark);
                }
            }

            return SpireReturn.Continue();
        }


    }


    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderInLibrary"
    )
    public static class RenderInLibraryUpgradeB {
        @SpireInsertPatch(rloc = 14)
        public static SpireReturn<Void> Insert(AbstractCard _instance,
                                               SpriteBatch sb) {
            if (_instance instanceof AbstractInheritCard) {
                if (DarkFormLibraryPatches.isViewingDark != ((AbstractInheritCard) _instance).isDark) {
                    ((AbstractInheritCard) _instance).initializeForm(DarkFormLibraryPatches.isViewingDark);
                }
            }
            return SpireReturn.Continue();
        }
    }
}

