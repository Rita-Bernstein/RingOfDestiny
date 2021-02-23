package RingOfDestiny.patches;

import RingOfDestiny.monster.IdeologyCorridor.SpiderQueen;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.ui.panels.PotionPopUp;
import javassist.CtBehavior;


public class CantBeTargetedPatch {

    @SpirePatch(
            clz = PotionPopUp.class,
            method = "updateTargetMode"

    )
// A patch to make allies untargetable by POTIONS
    public static class SpiderQueenCantBeTargetByPOTIONS {
        @SpireInsertPatch(locator = PotionLocator.class, localvars = {"hoveredMonster"})
        public static void MakeHoveredMonsterNull(PotionPopUp instance, @ByRef AbstractMonster[] hoveredMonster) {
            if (hoveredMonster[0] instanceof SpiderQueen) {
                SpiderQueen spiderQueen = (SpiderQueen)hoveredMonster[0];
                if (spiderQueen.halfDead) {
                    hoveredMonster[0] = null;
                }
            }
        }


        private static class PotionLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(InputHelper.class, "justClickedLeft");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }



    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "updateSingleTargetInput"

    )
// A patch to make allies untargetable by the player
    public static class SpiderQueenUntargetable {
        @SpireInsertPatch(locator = Locator.class, localvars = {"hoveredMonster"})
        public static void MakeHoveredMonsterNull(AbstractPlayer instance, @ByRef AbstractMonster[] hoveredMonster) {
            if (hoveredMonster[0] instanceof SpiderQueen) {
                SpiderQueen spiderQueen = (SpiderQueen)hoveredMonster[0];
                if (spiderQueen.halfDead) {
                    hoveredMonster[0] = null;
                }
            }

        }
        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(MonsterGroup.class, "areMonstersBasicallyDead");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

}


