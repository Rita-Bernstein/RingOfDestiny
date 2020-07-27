package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AddDiamondAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import RingOfDestiny.cards.AbstractRingCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Purification extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("Purification");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/45.png");
    private static final int COST = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Purification() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
        this.cardsToPreview = new Wound();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, this.magicNumber));
        addToBot(new MakeTempCardInDrawPileAction(new Wound(), 1, true, true));
    }


    public AbstractCard makeCopy() {
        return new Purification();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
			upgradeMagicNumber(1);
        }
    }
}