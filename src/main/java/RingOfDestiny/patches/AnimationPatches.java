package RingOfDestiny.patches;

import RingOfDestiny.character.AbstractRingCharacter;
import RingOfDestiny.powers.AbstractRingPower;
import RingOfDestiny.subEnergy.SubEnergy;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashPowerEffect;
import com.megacrit.cardcrawl.vfx.combat.GainPowerEffect;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;


public class AnimationPatches {
    @SpirePatch(
            clz = FlashPowerEffect.class,
            method = SpirePatch.CONSTRUCTOR
    )
    public static class GainPowerEffectPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(FlashPowerEffect _instance, AbstractPower power) {
            if (power instanceof AbstractRingPower && power.owner.isPlayer)
                _instance.isDone = true;
            return SpireReturn.Continue();
        }
    }

//
//    @SpirePatch(
//            clz = AbstractPlayer.class,
//            method = "useCard"
//    )
//    public static class UseCardPatch {
//        public static ExprEditor Instrument() {
//            return new ExprEditor() {
//                @Override
//                public void edit(MethodCall m) throws CannotCompileException {
//                    if (m.getMethodName().equals("useFastAttackAnimation")) {
//                        m.replace(
//                                "if(!($0 instanceof " + AbstractRingCharacter.class.getName() + "))"
//                                        + "$proceed($$);"
//                        );
//                    }
//                }
//            };
//        }
//    }
}


