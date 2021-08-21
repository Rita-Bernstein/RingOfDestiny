package RingOfDestiny.patches;

import RingOfDestiny.powers.AbstractRingPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashPowerEffect;


public class CalculateDamagePatch {
    @SpirePatch(
            clz = AbstractMonster.class,
            method = "calculateDamage"
    )
    public static class GainPowerEffectPatch {
        @SpireInsertPatch(rloc = 14, localvars = {"tmp"})
        public static SpireReturn<Void> Insert(AbstractMonster _instance, int damage, @ByRef float[] tmp) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof AbstractRingPower) {
                    tmp[0] = ((AbstractRingPower) p).atSingleDamageGive(_instance, tmp[0], DamageInfo.DamageType.NORMAL);
                }
            }
            return SpireReturn.Continue();
        }
    }
}


