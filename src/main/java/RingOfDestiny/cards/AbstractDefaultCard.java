package RingOfDestiny.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;


public abstract class AbstractDefaultCard
        extends CustomCard {
    public int defaultSecondMagicNumber;
    public int defaultBaseSecondMagicNumber;
    public boolean upgradedDefaultSecondMagicNumber;
    public boolean isDefaultSecondMagicNumberModified;

    public AbstractDefaultCard(String id, String img, int cost, AbstractCard.CardType type, AbstractCard.CardColor color, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target) {
        super(id, (CardCrawlGame.languagePack.getCardStrings(id)).NAME, img, cost, (CardCrawlGame.languagePack.getCardStrings(id)).DESCRIPTION, type, color, rarity, target);


        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
        this.isDamageModified = false;
        this.isBlockModified = false;
        this.isMagicNumberModified = false;
        this.isDefaultSecondMagicNumberModified = false;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (this.upgradedDefaultSecondMagicNumber) {
            this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber;
            this.isDefaultSecondMagicNumberModified = true;
        }
    }


    public void upgradeDefaultSecondMagicNumber(int amount) {
        this.defaultBaseSecondMagicNumber += amount;
        this.defaultSecondMagicNumber = this.defaultBaseSecondMagicNumber;
        this.upgradedDefaultSecondMagicNumber = true;
    }
}


