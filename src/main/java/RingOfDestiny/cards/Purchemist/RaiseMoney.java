package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AddDiamondAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import RingOfDestiny.cards.AbstractRingCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class RaiseMoney extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("RaiseMoney");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/10.png");
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public RaiseMoney() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
        this.cardsToPreview = new DrawAgain();
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = new DrawAgain();
        if (upgraded) c.upgrade();
        addToBot(new MakeTempCardInHandAction(c, this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return new RaiseMoney();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
			this.cardsToPreview.upgrade();
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			initializeDescription();
        }
    }
}