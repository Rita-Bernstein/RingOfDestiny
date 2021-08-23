package RingOfDestiny.patches;

import RingOfDestiny.character.AbstractRingCharacter;
import RingOfDestiny.powers.AbstractRingPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashPowerEffect;


public class OnAfterUseCardPatch {
    @SpirePatch(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class OnAfterUseCard {
        @SpireInsertPatch(rloc = 1, localvars = {"targetCard"})
        public static SpireReturn<Void> Insert(UseCardAction _instance, @ByRef AbstractCard[] targetCard) {
            if (AbstractDungeon.player instanceof AbstractRingCharacter)
                ((AbstractRingCharacter) AbstractDungeon.player).onAfterUseCard(targetCard[0], _instance);
            return SpireReturn.Continue();
        }
    }

}


