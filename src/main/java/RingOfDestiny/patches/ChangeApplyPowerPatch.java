package RingOfDestiny.patches;

import RingOfDestiny.powers.*;
import RingOfDestiny.powers.Monster.KnowledgeHall.FulPower;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.TextAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;



public class ChangeApplyPowerPatch {
    public static boolean extraPoisonApplyed = false;

    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCreature.class, AbstractCreature.class, AbstractPower.class, int.class, boolean.class, AbstractGameAction.AttackEffect.class}

    )
    public static class GasBombExtraPoison {
        @SpireInsertPatch(rloc = 24, localvars = {"amount","duration"})
        public static SpireReturn<Void> Insert(ApplyPowerAction _instance,
                                               AbstractCreature target,
                                               AbstractCreature source,
                                               AbstractPower powerToApply,
                                               int stackAmount,
                                               boolean isFast,
                                               AbstractGameAction.AttackEffect effect, @ByRef int[] amount,@ByRef float[] duration) {
//            中毒相关
            if (AbstractDungeon.player.hasPower(GasBombPower.POWER_ID) && source != null && source.isPlayer && target != source && powerToApply.ID.equals(PoisonPower.POWER_ID)) {
                AbstractDungeon.player.getPower(GasBombPower.POWER_ID).flash();
                powerToApply.amount += AbstractDungeon.player.getPower(GasBombPower.POWER_ID).amount;
                amount[0] = amount[0] + AbstractDungeon.player.getPower(GasBombPower.POWER_ID).amount;
            }


            if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                    if (!monster.isDead && !monster.isDying) {
                        if (monster.hasPower(InfectMarkPower.POWER_ID) && powerToApply.ID.equals(PoisonPower.POWER_ID)) {
                            monster.getPower(InfectMarkPower.POWER_ID).flash();
                            if (!extraPoisonApplyed) {
                                extraPoisonApplyed = true;
                                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, null, new PoisonPower(monster, null, amount[0]), amount[0]));
                            } else {
                                extraPoisonApplyed = false;
                            }
                        }
                    }
                }
            }
//            中毒相关

//            刻印相关
            if (AbstractDungeon.player.hasPower(EnchantmentPower.POWER_ID) && source != null && source.isPlayer && target != source && powerToApply.ID.equals(EtchPower.POWER_ID)) {
                AbstractDungeon.player.getPower(EnchantmentPower.POWER_ID).flash();
                powerToApply.amount += AbstractDungeon.player.getPower(EnchantmentPower.POWER_ID).amount;
                amount[0] = amount[0] + AbstractDungeon.player.getPower(EnchantmentPower.POWER_ID).amount;
            }
//            刻印相关

//            流血相关
            if (AbstractDungeon.player.hasPower(BloodmournePower.POWER_ID) && source != null && source.isPlayer && target != source && powerToApply.ID.equals(BleedingPower.POWER_ID)) {
                AbstractDungeon.player.getPower(BloodmournePower.POWER_ID).flash();
                powerToApply.amount += AbstractDungeon.player.getPower(BloodmournePower.POWER_ID).amount;
                amount[0] = amount[0] + AbstractDungeon.player.getPower(BloodmournePower.POWER_ID).amount;
            }
//            流血相关

            //            邪能：免疫易伤虚弱并获得力量
            if (target.hasPower(FulPower.POWER_ID) && (powerToApply.ID.equals(VulnerablePower.POWER_ID) || powerToApply.ID.equals(WeakPower.POWER_ID))) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(target, target, new StrengthPower(target, target.getPower(FulPower.POWER_ID).amount), target.getPower(FulPower.POWER_ID).amount));
                AbstractDungeon.actionManager.addToTop(new TextAboveCreatureAction(target, CardCrawlGame.languagePack.getUIString("ApplyPowerAction").TEXT[1]));
                duration[0] -= Gdx.graphics.getDeltaTime();
            }
            //            邪能：免疫易伤虚弱并获得力量


            return SpireReturn.Continue();
        }
    }

}


