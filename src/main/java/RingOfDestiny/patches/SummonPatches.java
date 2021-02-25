package RingOfDestiny.patches;


import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.monster.AbstractRingMonster;
import RingOfDestiny.screens.SummonSelectScreen;
import RingOfDestiny.summon.*;

import RingOfDestiny.ui.SoulStoneTutorial;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.daily.mods.AbstractDailyMod;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.options.SettingsScreen;
import com.megacrit.cardcrawl.ui.buttons.PeekButton;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import javassist.CtBehavior;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static com.megacrit.cardcrawl.helpers.ModHelper.enabledMods;

public class SummonPatches {
    //    ============实际宠物
    @SpirePatch(
            clz = AbstractPlayer.class,
            method = SpirePatch.CLASS
    )
    public static class AbstractPlayerSummonField {
        public static SpireField<AbstractSummon> summon = new SpireField<>(() -> new NullSummon());
    }


    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "onVictory"
    )
    public static class OnVictoryPatch {
        @SpireInsertPatch(rloc = 1)
        public static SpireReturn<Void> Insert(AbstractPlayer _instance) {
            AbstractPlayerSummonField.summon.set(_instance, new NullSummon());

            return SpireReturn.Continue();
        }
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

    @SpirePatch(
            clz = UseCardAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCard.class, AbstractCreature.class}
    )
    public static class UseCardActionPatch {
        @SpireInsertPatch(rloc = 16)
        public static SpireReturn<Void> Insert(UseCardAction _instance, AbstractCard card, AbstractCreature target) {
            AbstractPlayerSummonField.summon.get(AbstractDungeon.player).onUseCard(card, _instance);

            if (!getMonsters().areMonstersBasicallyDead()) {
                for (AbstractMonster monster : getMonsters().monsters) {
                    if (!monster.isDead && !monster.isDying && monster instanceof AbstractRingMonster) {
                        ((AbstractRingMonster)monster).onUseCard(card,_instance);
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "damage",
            paramtypez = {DamageInfo.class}
    )
    public static class OnAttackedPatch {
        @SpireInsertPatch(rloc = 16, localvars = {"damageAmount"})
        public static SpireReturn<Void> Insert(AbstractPlayer _instance, DamageInfo info, int damageAmount) {
            AbstractPlayerSummonField.summon.get(_instance).onAttacked(info, damageAmount);

            return SpireReturn.Continue();
        }
    }
//    ============实际宠物

    //    ============选择界面
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = SpirePatch.CLASS
    )
    public static class SummonSelectScreenField {
        public static SpireField<SummonSelectScreen> summonSelectScreen = new SpireField<>(() -> new SummonSelectScreen());
    }


    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfCombatLogic"
    )
    public static class applyStartOfCombatLogicPatch {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<Void> Insert(AbstractPlayer _instance) {

            if (_instance.chosenClass == AbstractPlayerEnum.Summoner
                    || ModHelper.isModEnabled("Diverse")
                    || ModHelper.isModEnabled("Summoner" + "Modded Character Cards")
                    || _instance.hasRelic(PrismaticShard.ID)){

                SummonSelectScreenField.summonSelectScreen.get(CardCrawlGame.dungeon).open();

                if (!RingOfDestiny.neverSeeSoulStoneTutorial) {
                    AbstractDungeon.ftue = new SoulStoneTutorial();
                    RingOfDestiny.neverSeeSoulStoneTutorial = true;
                    RingOfDestiny.neverSeeSoulStoneTutorialSwitch.toggle.enabled = true;
                    RingOfDestiny.saveSettings();
                }
            }


            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "update"
    )
    public static class SummonSelectUpdatePatch {
        @SpireInsertPatch(rloc = 22)
        public static SpireReturn<Void> Insert(AbstractDungeon _instance) {
            if (AbstractDungeon.screen == CustomCurrentScreenEnum.SummonSelect) {
                SummonSelectScreenField.summonSelectScreen.get(_instance).update();
                AbstractDungeon.currMapNode.room.update();
            }


            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "render"
    )
    public static class SummonSelectRenderPatch {
        @SpireInsertPatch(rloc = 45)
        public static SpireReturn<Void> Insert(AbstractDungeon _instance, SpriteBatch sb) {
            if (AbstractDungeon.screen == CustomCurrentScreenEnum.SummonSelect) {
                SummonSelectScreenField.summonSelectScreen.get(_instance).render(sb);
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "closeCurrentScreen"
    )
    public static class SummonSelectCloseCurrentScreenPatch {
        @SpireInsertPatch(rloc = 5)
        public static SpireReturn<Void> Insert() {
            if (AbstractDungeon.screen == CustomCurrentScreenEnum.SummonSelect) {
                genericScreenOverlayReset();
                AbstractDungeon.dynamicBanner.hide();
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "openPreviousScreen"
    )
    public static class OpenPreviousScreenPatch {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<Void> Insert(CurrentScreen s) {
            if (s == CustomCurrentScreenEnum.SummonSelect) {
                SummonSelectScreenField.summonSelectScreen.get(CardCrawlGame.dungeon).reopen();
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = TopPanel.class,
            method = "updateSettingsButtonLogic"
    )
    public static class UpdateSettingsButtonLogicPatch {
        @SpireInsertPatch(locator = SummonScreenLocator.class)
//        @SpireInsertPatch(rloc = 128)
        public static SpireReturn<Void> Insert(TopPanel _instance) {
            if (AbstractDungeon.screen == CustomCurrentScreenEnum.SummonSelect) {
                AbstractDungeon.previousScreen = CustomCurrentScreenEnum.SummonSelect;
                AbstractDungeon.dynamicBanner.hide();
            }

            return SpireReturn.Continue();
        }
    }

    private static class SummonScreenLocator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(SettingsScreen.class, "open");
            int[] lines = LineFinder.findAllInOrder(ctMethodToPatch, methodCallMatcher);
            return new int[]{lines[lines.length - 1]};
        }
    }


    public static void genericScreenOverlayReset() {
        if (AbstractDungeon.previousScreen == null) {
            if (AbstractDungeon.player.isDead) {
                previousScreen = AbstractDungeon.CurrentScreen.DEATH;
            } else {
                AbstractDungeon.isScreenUp = false;
                AbstractDungeon.overlayMenu.hideBlackScreen();
            }
        }

        if (getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.player.isDead) {
            AbstractDungeon.overlayMenu.showCombatPanels();
        }

    }
}


