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


public class ManaEnhancePowerPatches {


    @SpirePatch(
            clz = AbstractCard.class,
            method = "update"
    )
    public static class DiamondManagerStartOfTurnPatch {
        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(AbstractCard _instance) {
            if(AbstractDungeon.player != null){
                if (_instance.type == AbstractCard.CardType.ATTACK) {
                    if (AbstractDungeon.player.hasPower(UpgradedManaEnhancePower.POWER_ID)) {
                        _instance.costForTurn = UpgradedManaEnhancePower.changeCost(_instance.cost);
                    } else if (AbstractDungeon.player.hasPower(ManaEnhancePower.POWER_ID)) {
                        _instance.costForTurn = ManaEnhancePower.changeCost(_instance.cost);
                    }
                }


                if (_instance.cost != _instance.costForTurn) {
                    _instance.isCostModified = true;
                }
            }


            return SpireReturn.Continue();
        }
    }
}


