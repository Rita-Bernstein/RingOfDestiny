package RingOfDestiny.actions.Summoner;

import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MetallicizePower;

import java.util.ArrayList;

public class DemonicAction extends AbstractGameAction {
    private int blockPerCard;
    private AbstractCard.CardType typeForCheck;

    public DemonicAction(int blockAmount,AbstractCard.CardType typeForCheck) {
        this.blockPerCard = blockAmount;
        this.typeForCheck = typeForCheck;
        setValues(AbstractDungeon.player, AbstractDungeon.player);
        this.actionType = AbstractGameAction.ActionType.BLOCK;
    }


    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList<AbstractCard>();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type != typeForCheck) {
                cardsToExhaust.add(c);
            }
        }

        int amount = 0;
        for (AbstractCard c : cardsToExhaust) {
            amount ++;
        }

        if(amount > 0){
            addToTop(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new MetallicizePower(AbstractDungeon.player,amount * this.blockPerCard),amount*this.blockPerCard));
        }

        for (AbstractCard c : cardsToExhaust) {
            addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }
        this.isDone = true;
    }
}


