package RingOfDestiny.patches;

import RingOfDestiny.powers.AbstractRingPower;
import com.evacipated.cardcrawl.modthespire.lib.*;;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class OnManualDiscardPatch {

    @SpirePatch(
            clz = GameActionManager.class,
            method = "incrementDiscard",
            paramtypez = {boolean.class}
                )
    public static class incrementDiscardPatch {
        @SpireInsertPatch(rloc = 7)
        public static SpireReturn<Void> Insert(boolean endOfTurn) {

            if (!AbstractDungeon.actionManager.turnHasEnded && !endOfTurn) {
                   for (AbstractPower power : AbstractDungeon.player.powers) {
                       if(power instanceof AbstractRingPower)
                           ((AbstractRingPower) power).onManualDiscard();
                       }}

            return SpireReturn.Continue();
        }
    }

}


