package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.ExtractionAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Extraction extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("Extraction");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/44.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;



    public Extraction() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 5;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new ExtractionAction(p,this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Extraction();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(3);
        }
    }

}
