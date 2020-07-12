package RingOfDestiny.patches;

import RingOfDestiny.powers.GasBombPower;
import RingOfDestiny.powers.InfectMarkPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;


public class GasBombPowerPatch {
    public static boolean extraPoisonApplyed = false;
    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCreature.class, AbstractCreature.class, AbstractPower.class, int.class, boolean.class, AbstractGameAction.AttackEffect.class}

    )
    public static class GasBombExtraPoison {
        @SpireInsertPatch(rloc = 24, localvars = {"amount"})
        public static SpireReturn<Void> Insert(ApplyPowerAction _instance,
                                               AbstractCreature target,
                                               AbstractCreature source,
                                               AbstractPower powerToApply,
                                               int stackAmount,
                                               boolean isFast,
                                               AbstractGameAction.AttackEffect effect, @ByRef int[] amount) {

            if (AbstractDungeon.player.hasPower(GasBombPower.POWER_ID) && source != null && source.isPlayer && target != source && powerToApply.ID.equals("Poison")) {
                AbstractDungeon.player.getPower(GasBombPower.POWER_ID).flash();
                powerToApply.amount += AbstractDungeon.player.getPower(GasBombPower.POWER_ID).amount;
                amount[0] = amount[0] + AbstractDungeon.player.getPower(GasBombPower.POWER_ID).amount;
            }


            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                    if (!monster.isDead && !monster.isDying) {
                        if (monster.hasPower(InfectMarkPower.POWER_ID) && powerToApply.ID.equals("Poison")) {
                            monster.getPower(InfectMarkPower.POWER_ID).flash();
                            if(!extraPoisonApplyed){
                                extraPoisonApplyed = true;
                                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, null, new PoisonPower(monster, null, amount[0]), amount[0]));
                            }else {
                                extraPoisonApplyed = false;
                            }
                        }
                    }
                }
            }


            return SpireReturn.Continue();
        }
    }

}


