package RingOfDestiny.actions.MagicBullet;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class ChangeCostAction extends AbstractGameAction {
    UUID uuid;
    private AbstractCard card;

    public ChangeCostAction(UUID targetUUID, int amount) {
        this.card = null;
        this.uuid = targetUUID;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }


    public void update() {
        if (this.card == null) {
            for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
                c.modifyCostForCombat(this.amount);
            }
        } else {
            this.card.modifyCostForCombat(this.amount);
        }

        this.isDone = true;
    }
}


