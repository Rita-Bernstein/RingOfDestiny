package RingOfDestiny.patches;

import RingOfDestiny.powers.ShadowMarkPower;
import RingOfDestiny.relics.ShadowKunai;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;


public class ShadowMarkPatch {
    @SpirePatch(
            clz = AbstractMonster.class,
            method = "damage",
            paramtypez = {DamageInfo.class}

    )
    public static class GasBombExtraPoison {
        @SpireInsertPatch(rloc = 5, localvars = {"damageAmount"})
        public static SpireReturn<Void> Insert(AbstractMonster _instance, DamageInfo info, @ByRef int[] damageAmount) {
            if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS) {
                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                        if (!monster.isDead && !monster.isDying && monster.hasPower(ShadowMarkPower.POWER_ID)) {
                            ((TwoAmountPower) monster.getPower(ShadowMarkPower.POWER_ID)).amount2 += (int)Math.floor(damageAmount[0] * 1.5f);
                        }
                    }
                }

                if(AbstractDungeon.player.hasRelic(ShadowKunai.ID)){
                    AbstractDungeon.player.getRelic(ShadowKunai.ID).counter += damageAmount[0];
                }
            }
            return SpireReturn.Continue();
        }
    }

}


