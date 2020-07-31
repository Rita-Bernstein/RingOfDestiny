package RingOfDestiny.patches;


import RingOfDestiny.screens.SummonSelectScreen;
import RingOfDestiny.summon.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.buttons.PeekButton;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;

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
            method = "applyPreCombatLogic"
    )
    public static class ApplyPreCombatLogicPatch {
        @SpireInsertPatch(rloc = 0)
        public static SpireReturn<Void> Insert(AbstractPlayer _instance) {
            SummonSelectScreenField.summonSelectScreen.get(CardCrawlGame.dungeon).open();

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
                AbstractDungeon.overlayMenu.showCombatPanels();
                AbstractDungeon.dynamicBanner.hide();
                genericScreenOverlayReset();
            }

            return SpireReturn.Continue();
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


