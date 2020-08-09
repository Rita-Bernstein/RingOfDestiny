package RingOfDestiny.patches;


import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.powers.AbstractRingPower;

import RingOfDestiny.relics.AbstractRingRelic;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class PurgeOnUseCardPatches {

    @SpirePatch(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class UseCardActionUpdatePatch {
        @SpireInsertPatch(rloc = 17, localvars = {"targetCard"})
        public static SpireReturn<Void> Insert(UseCardAction _instance, AbstractCard targetCard) {
            if (targetCard instanceof AbstractRingCard) {
                AbstractRingCard c = ((AbstractRingCard) targetCard);
                if (c.isDestructive) {
                    AbstractDungeon.actionManager.addToTop(new ShowCardAndPoofAction(targetCard));
                    _instance.isDone = true;
                    AbstractDungeon.player.cardInUse = null;

                    for (AbstractPower p : AbstractDungeon.player.powers) {
                        if (p instanceof AbstractRingPower) {
                            ((AbstractRingPower) p).onDestructive();
                        }
                    }

                    for (AbstractRelic r : AbstractDungeon.player.relics) {
                        if (r instanceof AbstractRingRelic) {
                            ((AbstractRingRelic) r).onDestructive();
                        }
                    }


                    return SpireReturn.Return(null);
                }
            }


            return SpireReturn.Continue();
        }
    }


}


