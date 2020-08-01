package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DoomsdayProtection extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("DoomsdayProtection");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/27.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public DoomsdayProtection() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = 9;
        this.magicNumber = this.baseMagicNumber = 2;
        this.cardsToPreview = new DoomsdayMeteorite();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new GainBlockAction(p, p, this.block));
        AbstractCard s = (new DoomsdayMeteorite()).makeCopy();
        if(upgraded) s.upgrade();
        addToBot(new MakeTempCardInDiscardAction(s, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new DoomsdayProtection();
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
