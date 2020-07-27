package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AddDiamondAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FirstInvest extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("FirstInvest");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/02.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;



    public FirstInvest() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = 8;
        this.magicNumber = this.baseMagicNumber = 1;
        this.cardsToPreview = new FifteenDollar();
        this.purgeOnUse = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddDiamondAction(this.magicNumber));
        addToBot(new GainBlockAction(p, p, this.block));
        AbstractCard c = (new FifteenDollar()).makeCopy();
        if(upgraded) c.upgrade();
        addToBot(new MakeTempCardInHandAction(c,1));
    }

    public AbstractCard makeCopy() {
        return new FirstInvest();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
