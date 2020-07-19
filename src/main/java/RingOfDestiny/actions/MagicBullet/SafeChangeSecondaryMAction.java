package RingOfDestiny.actions.MagicBullet;

import RingOfDestiny.cards.AbstractRingCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class SafeChangeSecondaryMAction extends AbstractGameAction {
    UUID uuid;
    private AbstractCard card;

    public SafeChangeSecondaryMAction(UUID targetUUID, int amount) {
        this.card = null;
        this.uuid = targetUUID;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public SafeChangeSecondaryMAction(AbstractCard card) {
        this.card = card;
    }

    public void update() {
        if (this.card == null) {
            for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
                if(c instanceof AbstractRingCard)
                    ((AbstractRingCard)c).sefeChangeSecondM(this.amount);
            }
        } else {
            if(this.card instanceof AbstractRingCard)
                ((AbstractRingCard)this.card).sefeChangeSecondM(this.amount);
        }

        this.isDone = true;
    }
}


