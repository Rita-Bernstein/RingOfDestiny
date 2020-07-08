package RingOfDestiny.helpers;

import RingOfDestiny.cards.AbstractRingCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class SecondaryMagicVariable extends DynamicVariable {
    @Override
    public String key() {
        return "RODSecondM";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractRingCard) {
            AbstractRingCard asc = (AbstractRingCard) card;
            return asc.isSecondaryMModified;
        } else {
            return false;
        }
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractRingCard) {
            AbstractRingCard asc = (AbstractRingCard) card;
            return asc.secondaryM;
        } else {
            return 0;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractRingCard) {
            AbstractRingCard asc = (AbstractRingCard) card;
            return asc.secondaryM;
        } else {
            return 0;
        }
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractRingCard) {
            AbstractRingCard asc = (AbstractRingCard) card;
            return asc.isSecondaryMModified;
        } else {
            return false;
        }
    }
}