package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GreedySummon extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("GreedySummon");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/02.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public GreedySummon() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
        this.cardsToPreview = new PowerOfGreed();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard s = (new PowerOfGreed()).makeCopy();
        if(upgraded) s.upgrade();
        addToBot(new MakeTempCardInHandAction(s, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new GreedySummon();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
