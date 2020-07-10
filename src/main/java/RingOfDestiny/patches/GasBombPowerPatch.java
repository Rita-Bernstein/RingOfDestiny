package RingOfDestiny.patches;

import RingOfDestiny.RingOfDestiny;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static com.megacrit.cardcrawl.core.AbstractCreature.sr;

public class GasBombPowerPatch {

    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCreature.class, AbstractCreature.class, AbstractPower.class, int.class, boolean.class, AbstractGameAction.AttackEffect.class}

    )
    public static class GasBombExtraPoison {
        @SpireInsertPatch(rloc = 24,localvars = {"amount"})
        public static SpireReturn<Void> Insert(ApplyPowerAction __instance,
                                               AbstractCreature target,
                                               AbstractCreature source,
                                               AbstractPower powerToApply,
                                               int stackAmount,
                                               boolean isFast,
                                               AbstractGameAction.AttackEffect effect,@ByRef int[] amount) {

                 if (AbstractDungeon.player.hasPower("RingOfDestiny:GasBombPower") && source != null && source.isPlayer && target != source && powerToApply.ID.equals("Poison")) {
                       AbstractDungeon.player.getPower("RingOfDestiny:GasBombPower").flash();
                       powerToApply.amount += AbstractDungeon.player.getPower("RingOfDestiny:GasBombPower").amount;
                       amount[0] = amount[0] + AbstractDungeon.player.getPower("RingOfDestiny:GasBombPower").amount;
                     }

            return SpireReturn.Continue();
        }
    }

}


