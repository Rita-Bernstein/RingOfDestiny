package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.powers.BleedingPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class CorrosionAction extends AbstractGameAction {
    private int amount;

    public CorrosionAction(AbstractMonster m,int amount) {
        this.amount = amount;
        setValues(m, AbstractDungeon.player);
        this.actionType = ActionType.DEBUFF;
    }


    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList<AbstractCard>();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type != AbstractCard.CardType.ATTACK) {
                cardsToExhaust.add(c);
            }
        }


        for (AbstractCard c : cardsToExhaust) {
            addToTop(new ApplyPowerAction(this.target,this.source,new BleedingPower(this.target,this.source,this.amount),this.amount));
        }

        for (AbstractCard c : cardsToExhaust) {
            addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }
        this.isDone = true;
    }
}


