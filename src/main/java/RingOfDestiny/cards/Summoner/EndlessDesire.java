package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EndlessDesire extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("EndlessDesire");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/07.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public EndlessDesire() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new GainBlockAction(p, p, this.block));
        AbstractCard s = (new EndlessDesire()).makeCopy();
        if(upgraded) s.upgrade();
        addToBot(new MakeTempCardInHandAction(s, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new EndlessDesire();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
