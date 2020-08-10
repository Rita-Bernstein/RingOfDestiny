package RingOfDestiny.helpers;


import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.AbstractRingPower;
import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class SubEnergyModifier extends AbstractCardModifier implements AlternateCardCostModifier {
    protected static final String[] oriCantUseMessage = CardCrawlGame.languagePack.getUIString("SingleCardViewPopup").TEXT;

    public SubEnergyModifier() {
        this.priority = -1;
    }


    @Override
    public int getAlternateResource(AbstractCard card) {
        return EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).totalCount;
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        if (card instanceof AbstractInheritCard) {
            return ((AbstractInheritCard) card).isDark;
        } else {
            return false;
        }
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        boolean canUse = super.canPlayCard(card);
        if (card instanceof AbstractInheritCard) {
            AbstractInheritCard c = ((AbstractInheritCard) card);
            if (c.isDark) {
                if (!c.hasEnoughSubEnergy(c.subCostForTurn))
                    canUse = false;
            } else {
                if (!hasEnoughEnergy(c)) {
                    canUse = false;
                }
            }
        }
        return canUse;
    }


    @Override
    public int spendAlternateCost(AbstractCard card, int costToSpend) {
        if (card instanceof AbstractInheritCard) {
            AbstractInheritCard c = ((AbstractInheritCard) card);
            int resource = EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).totalCount;

            if (resource >= c.subCostForTurn) {
                AbstractDungeon.actionManager.addToBottom(new UseSubEnergyAction(c.subCostForTurn));
                costToSpend = 0;
            }
        }
        return costToSpend;
    }


    @Override
    public AbstractCardModifier makeCopy() {
        return new SubEnergyModifier();
    }

    public boolean hasEnoughEnergy(AbstractInheritCard card) {
        if (AbstractDungeon.actionManager.turnHasEnded) {
            card.cantUseMessage = oriCantUseMessage[9];
            return false;
        }

        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (!p.canPlayCard(card)) {
                card.cantUseMessage = oriCantUseMessage[13];
                return false;
            }
        }

        if (AbstractDungeon.player.hasPower("Entangled") && card.type == AbstractCard.CardType.ATTACK) {
            card.cantUseMessage = oriCantUseMessage[10];
            return false;
        }

        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof AbstractRingPower) {
                AbstractRingPower power = (AbstractRingPower) p;

                if (!power.canPlayCard(card)) {
                    card.cantUseMessage = power.getCantPlayMessage();
                    SpireReturn.Return(false);
                }
            }
        }

        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (!r.canPlay(card)) {
                return false;
            }
        }

        for (AbstractBlight b : AbstractDungeon.player.blights) {
            if (!b.canPlay(card)) {
                return false;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!c.canPlay(card)) {
                return false;
            }
        }

        if (EnergyPanel.totalCount >= card.costForTurn || card.freeToPlay() || card.isInAutoplay) {
            return true;
        }

        card.cantUseMessage = oriCantUseMessage[11];
        return false;
    }
}