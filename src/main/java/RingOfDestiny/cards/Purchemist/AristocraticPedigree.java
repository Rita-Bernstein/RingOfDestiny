package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.TempIncreaseMaxHPAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AristocraticPedigree extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("AristocraticPedigree");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/27.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;


    public AristocraticPedigree() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new TempIncreaseMaxHPAction(this.magicNumber, p));
    }

    public AbstractCard makeCopy() {
        return new AristocraticPedigree();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(5);
        }
    }

}
