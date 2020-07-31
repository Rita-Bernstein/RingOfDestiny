package RingOfDestiny.patches;

import RingOfDestiny.powers.summon.ManaControlPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

public class CorruptionPatches {
    @SpirePatch(
            clz = ShowCardAndAddToHandEffect.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCard.class, float.class, float.class}


    )
    public static class ShowCardAndAddToHandEffectPatch {
        @SpirePostfixPatch
        public static SpireReturn<Void> Postfix(ShowCardAndAddToHandEffect _instance, AbstractCard card, float offsetX, float offsetY) {

            if (AbstractDungeon.player.hasPower(ManaControlPower.POWER_ID) && card.type == AbstractCard.CardType.SKILL && card.color == AbstractDungeon.player.getCardColor()) {
                setCostForTurnManaControl(card);
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = ShowCardAndAddToHandEffect.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCard.class}


    )
    public static class AnotherShowCardAndAddToHandEffectPatch {
        @SpirePostfixPatch
        public static SpireReturn<Void> Postfix(ShowCardAndAddToHandEffect _instance, AbstractCard card) {

            if (AbstractDungeon.player.hasPower(ManaControlPower.POWER_ID) && card.type == AbstractCard.CardType.SKILL && card.color == AbstractDungeon.player.getCardColor()) {
                setCostForTurnManaControl(card);
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = SpirePatch.CONSTRUCTOR,
            paramtypez = {AbstractCreature.class, AbstractCreature.class, AbstractPower.class, int.class, boolean.class, AbstractGameAction.AttackEffect.class}


    )
    public static class ApplyPowerActionPatch {
        @SpireInsertPatch(rloc = 24)
        public static SpireReturn<Void> Insert(ApplyPowerAction _instance, AbstractCreature target,
                                               AbstractCreature source,
                                               AbstractPower powerToApply,
                                               int stackAmount, boolean isFast,
                                               AbstractGameAction.AttackEffect effect) {

            if (powerToApply.ID.equals(ManaControlPower.POWER_ID)) {
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c.color == AbstractDungeon.player.getCardColor() && c.type == AbstractCard.CardType.SKILL)
                        modifyCostForCombatManaControl(c);
                }
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c.color == AbstractDungeon.player.getCardColor() && c.type == AbstractCard.CardType.SKILL)
                        modifyCostForCombatManaControl(c);
                }
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c.color == AbstractDungeon.player.getCardColor() && c.type == AbstractCard.CardType.SKILL)
                        modifyCostForCombatManaControl(c);
                }
                for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
                    if (c.color == AbstractDungeon.player.getCardColor() && c.type == AbstractCard.CardType.SKILL) {
                        modifyCostForCombatManaControl(c);
                    }
                }
            }

            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = ExhumeAction.class,
            method = "update"
    )
    public static class ExhumeActionPatch {
        @SpireInsertPatch(rloc = 28, localvars = {"c"})
        public static SpireReturn<Void> Insert(ExhumeAction _instance, @ByRef(type = "cards.AbstractCard") Object[] card) {
            if (AbstractDungeon.player.hasPower(ManaControlPower.POWER_ID) && ((AbstractCard) card[0]).type == AbstractCard.CardType.SKILL && ((AbstractCard) card[0]).color == AbstractDungeon.player.getCardColor()) {
                setCostForTurnManaControl(((AbstractCard) card[0]));
            }

            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = ExhumeAction.class,
            method = "update"
    )
    public static class AnotherExhumeActionPatch {
        @SpireInsertPatch(rloc = 73, localvars = {"c"})
        public static SpireReturn<Void> Insert(ExhumeAction _instance, @ByRef(type = "cards.AbstractCard") Object[] card) {
            if (AbstractDungeon.player.hasPower(ManaControlPower.POWER_ID) && ((AbstractCard) card[0]).type == AbstractCard.CardType.SKILL && ((AbstractCard) card[0]).color == AbstractDungeon.player.getCardColor()) {
                setCostForTurnManaControl(((AbstractCard) card[0]));
            }

            return SpireReturn.Continue();
        }
    }

    public static void setCostForTurnManaControl(AbstractCard c) {
        if (c.costForTurn >= 2) {
            c.costForTurn--;

            if (c.costForTurn != c.cost) {
                c.isCostModifiedForTurn = true;
            }
        }

    }

    public static void modifyCostForCombatManaControl(AbstractCard c) {
        if (c.costForTurn >= 2) {
            c.costForTurn--;
            if (c.costForTurn < 0) {
                c.costForTurn = 0;
            }

            if (c.cost != c.costForTurn) {
                c.isCostModified = true;
            }
            c.cost = c.costForTurn;
        } else if (c.cost >= 2) {
            c.cost--;
            if (c.cost < 0) {
                c.cost = 0;
            }
            c.costForTurn = 0;
            if (c.cost != c.costForTurn) {
                c.isCostModified = true;
            }
        }
    }

}


