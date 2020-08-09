package RingOfDestiny.actions.Inherit;

import RingOfDestiny.cards.AbstractInheritCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class ChangeSubCostAction extends AbstractGameAction {
    UUID uuid;
    private AbstractCard card;

    public ChangeSubCostAction(UUID targetUUID, int amount) {
        this.card = null;
        this.uuid = targetUUID;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public ChangeSubCostAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        if (this.card == null) {
            for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
                if(c instanceof AbstractInheritCard){
                    ((AbstractInheritCard)c).modifySubCostForCombat(this.amount);
                }

            }
        } else {
            if(this.card instanceof AbstractInheritCard){
                ((AbstractInheritCard)this.card).modifySubCostForCombat(this.amount);
            }
        }

        this.isDone = true;
    }
}


