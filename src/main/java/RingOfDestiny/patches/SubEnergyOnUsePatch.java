package RingOfDestiny.patches;

import RingOfDestiny.cards.AbstractInheritCard;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public class SubEnergyOnUsePatch {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "useCard",
            paramtypez = {AbstractCard.class, AbstractMonster.class, int.class}
    )
    public static class useCardPatch {
        @SpireInsertPatch(rloc = 10)
        public static SpireReturn<Void> Insert(AbstractPlayer _instance,
                                               AbstractCard c, AbstractMonster monster, int energyOnUse) {
            if (c instanceof AbstractInheritCard) {
                AbstractInheritCard card = (AbstractInheritCard) c;
                if (card.subCost == -1 &&
                        EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).totalCount < card.subEnergyOnUse
                        && !card.ignoreSubEnergyOnUse) {
                    card.subEnergyOnUse = EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).totalCount;
                }
            }
            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = GameActionManager.class,
            method = "getNextAction"
    )
    public static class getNextActionPatchA {
        @SpireInsertPatch(rloc = 36)
        public static SpireReturn<Void> Insert(GameActionManager _instance) {
         if(_instance.cardQueue.get(0).card instanceof AbstractInheritCard){
             AbstractInheritCard c = (AbstractInheritCard)_instance.cardQueue.get(0).card;

             if (c.isInAutoplay) {
                 c.ignoreSubEnergyOnUse = true;
             } else {
                 c.ignoreSubEnergyOnUse = _instance.cardQueue.get(0).ignoreEnergyTotal;
             }

         }

            return SpireReturn.Continue();
        }
    }
}


