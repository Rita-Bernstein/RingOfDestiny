package RingOfDestiny.patches;

import RingOfDestiny.cards.Summoner.ManaEnhance;
import RingOfDestiny.powers.Summoner.ManaEnhancePower;
import RingOfDestiny.powers.Summoner.UpgradedManaEnhancePower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;


public class ManaEnhancePowerPatches {


    @SpirePatch(
            clz = AbstractCard.class,
            method = "freeToPlay"
    )
    public static class ManaEnhanceApplyPowersPatch {
        @SpireInsertPatch(rloc = 7)
        public static SpireReturn<Boolean> Insert(AbstractCard _instance) {
            if (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null &&
                    (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                    AbstractDungeon.player.hasPower(ManaEnhancePower.POWER_ID) && _instance.type == AbstractCard.CardType.ATTACK) {
                return SpireReturn.Return(true);
            }


            return SpireReturn.Continue();
        }
    }
}


