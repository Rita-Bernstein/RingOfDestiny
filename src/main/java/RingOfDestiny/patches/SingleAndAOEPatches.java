package RingOfDestiny.patches;

import RingOfDestiny.cards.AbstractRingCard;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;


public class SingleAndAOEPatches {

    @SpirePatch(
            clz = AbstractCard.class,
            method = "calculateCardDamage"
    )
    public static class SingleAndAOEDamagePatch {
        @SpirePostfixPatch
        public static SpireReturn<Void> Postfix(AbstractCard _instance, AbstractMonster mo) {
            if (_instance instanceof AbstractRingCard) {
                AbstractRingCard card = ((AbstractRingCard) _instance);

                if (card.isAnotherDamage) {
                    if (mo != null) {
                        float tmp = card.baseSecondaryM;

                        if (card.modifyAlterDamage)
                            tmp = card.getModifyAlterDamage();

                        for (AbstractRelic r : AbstractDungeon.player.relics) {
                            tmp = r.atDamageModify(tmp, _instance);
                            if (card.baseSecondaryM != (int) tmp) {
                                card.isSecondaryMModified = true;
                            }
                        }


                        for (AbstractPower p : AbstractDungeon.player.powers) {
                            tmp = p.atDamageGive(tmp, card.damageTypeForTurn, card);
                        }


                        tmp = AbstractDungeon.player.stance.atDamageGive(tmp, card.damageTypeForTurn, card);
                        if (card.baseSecondaryM != (int) tmp) {
                            card.isSecondaryMModified = true;
                        }


                        for (AbstractPower p : mo.powers) {
                            tmp = p.atDamageReceive(tmp, _instance.damageTypeForTurn, card);
                        }


                        for (AbstractPower p : AbstractDungeon.player.powers) {
                            tmp = p.atDamageFinalGive(tmp, _instance.damageTypeForTurn, card);
                        }


                        for (AbstractPower p : mo.powers) {
                            tmp = p.atDamageFinalReceive(tmp, card.damageTypeForTurn, card);
                        }


                        if (tmp < 0.0F) {
                            tmp = 0.0F;
                        }


                        if (card.baseSecondaryM != MathUtils.floor(tmp)) {
                            card.isSecondaryMModified = true;
                        }
                        card.secondaryM = MathUtils.floor(tmp);

                    }
                }


                if (card.isSingleAndAOE) {

                    ArrayList<AbstractMonster> m = (AbstractDungeon.getCurrRoom()).monsters.monsters;
                    float[] tmp = new float[m.size()];

                    for (int i = 0; i < tmp.length; i++) {
                        tmp[i] = card.baseSecondaryM;

                    }


                    if (card.modifyAlterDamage)
                        for (int i = 0; i < tmp.length; i++) {
                            tmp[i] = card.getModifyAlterDamage();
                        }


                    for (int i = 0; i < tmp.length; i++) {


                        for (AbstractRelic r : AbstractDungeon.player.relics) {
                            tmp[i] = r.atDamageModify(tmp[i], _instance);
                            if (card.baseSecondaryM != (int) tmp[i]) {
                                card.isSecondaryMModified = true;
                            }
                        }


                        for (AbstractPower p : AbstractDungeon.player.powers) {
                            tmp[i] = p.atDamageGive(tmp[i], card.damageTypeForTurn, card);
                        }


                        tmp[i] = AbstractDungeon.player.stance.atDamageGive(tmp[i], card.damageTypeForTurn, card);
                        if (card.baseSecondaryM != (int) tmp[i]) {
                            card.isSecondaryMModified = true;
                        }
                    }


                    for (int i = 0; i < tmp.length; i++) {
                        for (AbstractPower p : (m.get(i)).powers) {
                            if (!(m.get(i)).isDying && !(m.get(i)).isEscaping) {
                                tmp[i] = p.atDamageReceive(tmp[i], card.damageTypeForTurn, card);
                            }
                        }
                    }


                    for (int i = 0; i < tmp.length; i++) {
                        for (AbstractPower p : AbstractDungeon.player.powers) {
                            tmp[i] = p.atDamageFinalGive(tmp[i], card.damageTypeForTurn, card);
                        }
                    }


                    for (int i = 0; i < tmp.length; i++) {
                        for (AbstractPower p : (m.get(i)).powers) {
                            if (!(m.get(i)).isDying && !(m.get(i)).isEscaping) {
                                tmp[i] = p.atDamageFinalReceive(tmp[i], card.damageTypeForTurn, card);
                            }
                        }
                    }


                    for (int i = 0; i < tmp.length; i++) {
                        if (tmp[i] < 0.0F) {
                            tmp[i] = 0.0F;
                        }
                    }


                    card.isSingleAndAOEDamage = new int[tmp.length];
                    for (int i = 0; i < tmp.length; i++) {
                        if (card.baseSecondaryM != MathUtils.floor(tmp[i])) {
                            card.isSecondaryMModified = true;
                        }
                        card.isSingleAndAOEDamage[i] = MathUtils.floor(tmp[i]);
                    }
                    card.secondaryM = card.isSingleAndAOEDamage[0];


                }
            }
            return SpireReturn.Continue();
        }
    }
}


