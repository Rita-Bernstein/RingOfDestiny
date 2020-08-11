package RingOfDestiny.patches;

import RingOfDestiny.powers.AbstractRingPower;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;


public class AbstractRingPowerPatches {

    @SpirePatch(
            clz = CardCrawlGame.class,
            method = "create"
    )
    public static class initializePatch {
        @SpireInsertPatch(locator = TabNameLocator.class, localvars = {"tabName"})
        public static SpireReturn<Void> Insert() {
            AbstractRingPower.initialize();
            return SpireReturn.Continue();
        }


        private static class TabNameLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher.MethodCallMatcher methodCallMatcher = new Matcher.MethodCallMatcher(AbstractPower.class, "initialize");
                return LineFinder.findInOrder(ctMethodToPatch, methodCallMatcher);
            }
        }
    }
}


