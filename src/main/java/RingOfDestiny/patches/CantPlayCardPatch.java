package RingOfDestiny.patches;

import RingOfDestiny.powers.AbstractRingPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class CantPlayCardPatch {
    @SpirePatch(
            clz = AbstractCard.class,
            method = "hasEnoughEnergy"
    )
    public static class CantPlayCardPowerPatch {

        @SpirePrefixPatch
        public static SpireReturn<Boolean> Prefix(AbstractCard _instance) {
            for (AbstractPower p : AbstractDungeon.player.powers) {
                if (p instanceof AbstractRingPower) {
                    AbstractRingPower power = (AbstractRingPower) p;

                    if (!power.canPlayCard(_instance)) {
                        _instance.cantUseMessage = power.getCantPlayMessage();
                        SpireReturn.Return(false);
                    }
                }
            }

            return SpireReturn.Continue();
        }
    }
}


