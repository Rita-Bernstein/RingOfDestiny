package RingOfDestiny.patches;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.AddSoulStoneAction;
import RingOfDestiny.character.MagicBullet;
import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.*;
import RingOfDestiny.soulStone.SoulStone;
import RingOfDestiny.subEnergy.SubEnergy;
import RingOfDestiny.vfx.RefreshEnergyBetterEffect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.RefreshEnergyEffect;

import java.lang.reflect.Constructor;

import static basemod.BaseMod.getModdedCharacters;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;


public class SubEnergyVfxPatches {
    public static Color hideColor = Color.WHITE.cpy();
    public static int totalCountSaved = 0;

    @SpirePatch(
            clz = EnergyPanel.class,
            method = SpirePatch.CLASS
    )
    public static class ExtraEnergyPanelField {
        public static SpireField<Float> energyVfxAngle = new SpireField<>(() -> 0.0F);
        public static SpireField<Float> energyVfxScale = new SpireField<>(() -> Settings.scale);
        public static SpireField<Color> energyVfxColor = new SpireField<>(() -> Color.WHITE.cpy());


    }

    @SpirePatch(
            clz = EnergyPanel.class,
            method = "updateVfx"
    )
    public static class updateVfxPatch {
        @SpireInsertPatch(rloc = 4)
        public static SpireReturn<Void> Insert(EnergyPanel _instance) {
            if (EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(_instance)) {
                ExtraEnergyPanelField.energyVfxColor.get(_instance).a = Interpolation.exp10In.apply(0.5F, 0.0F, 1.0F - EnergyPanel.energyVfxTimer / 2.0F);
                ExtraEnergyPanelField.energyVfxAngle.set(_instance, ExtraEnergyPanelField.energyVfxAngle.get(_instance) + Gdx.graphics.getDeltaTime() * -30.0F);
                ExtraEnergyPanelField.energyVfxScale.set(_instance, Settings.scale * Interpolation.exp10In.apply(1.0F, 0.1F, 1.0F - EnergyPanel.energyVfxTimer / 2.0F));

            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = EnergyPanel.class,
            method = "updateVfx"
    )
    public static class AnotherUpdateVfxPatch {
        @SpireInsertPatch(rloc = 10)
        public static SpireReturn<Void> Insert(EnergyPanel _instance) {
            if (EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(_instance)) {
                ExtraEnergyPanelField.energyVfxColor.get(_instance).a = 0.0f;

            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = EnergyPanel.class,
            method = "renderVfx"
    )
    public static class ExtraEnergyPanelRenderPatch {
        @SpireInsertPatch(rloc = 3)
        public static SpireReturn<Void> Insert(EnergyPanel _instance, SpriteBatch sb) {
            if (EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(_instance)) {
                hideColor.a = 0.0f;
                sb.setColor(hideColor);
            }
            return SpireReturn.Continue();
        }
    }

    //光圈
    @SpirePatch(
            clz = RefreshEnergyEffect.class,
            method = "render"
    )
    public static class RefreshEnergyEffectPatch {
        @SpireInsertPatch(rloc = 2)
        public static SpireReturn<Void> Insert(RefreshEnergyEffect _instance, SpriteBatch sb) {
            if (EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(AbstractDungeon.overlayMenu.energyPanel)) {
                hideColor.a = 0.0f;
                sb.setColor(hideColor);
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = EnergyPanel.class,
            method = "setEnergy"
    )
    public static class setEnergyPatch {
        @SpireInsertPatch(rloc = 1)
        public static SpireReturn<Void> Insert(int energy) {
            if (EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(AbstractDungeon.overlayMenu.energyPanel)) {
                AbstractDungeon.effectsQueue.add(
                        new RefreshEnergyBetterEffect(
                                AbstractDungeon.overlayMenu.energyPanel.current_x - 50.0f * Settings.scale,
                                AbstractDungeon.overlayMenu.energyPanel.current_y - 50.0f * Settings.scale,
                                SubEnergy.imgScale * 2.0f));
            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = EnergyPanel.class,
            method = "addEnergy"
    )
    public static class addEnergyPatch {
        @SpireInsertPatch(rloc = 11)
        public static SpireReturn<Void> Insert(int e) {
            if (EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(AbstractDungeon.overlayMenu.energyPanel)) {
                AbstractDungeon.effectsQueue.add(
                        new RefreshEnergyBetterEffect(
                                AbstractDungeon.overlayMenu.energyPanel.current_x - 50.0f * Settings.scale,
                                AbstractDungeon.overlayMenu.energyPanel.current_y - 50.0f * Settings.scale,
                                SubEnergy.imgScale * 2.0f));
            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = EnergyPanel.class,
            method = "renderOrb"
    )
    public static class renderOrbPrefixPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(EnergyPanel _instance, SpriteBatch sb) {
            totalCountSaved = EnergyPanel.totalCount;
            if (EnergyPanelRenderPatches.PatchEnergyPanelField.isInDark.get(AbstractDungeon.overlayMenu.energyPanel)) {
                if (EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(_instance).totalCount > 0){
                    EnergyPanel.totalCount = 1;
                }else {
                    EnergyPanel.totalCount = 0;
                }

            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = EnergyPanel.class,
            method = "renderOrb"
    )
    public static class renderOrbPostfixPatch {
        @SpirePostfixPatch
        public static SpireReturn<Void> Postfix(EnergyPanel _instance, SpriteBatch sb) {
            EnergyPanel.totalCount = totalCountSaved;
            return SpireReturn.Continue();
        }
    }
}


