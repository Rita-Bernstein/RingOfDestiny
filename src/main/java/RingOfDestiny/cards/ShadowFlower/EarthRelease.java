package RingOfDestiny.cards.ShadowFlower;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.ShadowFlower.EarthReleaseAction;
import RingOfDestiny.actions.ShadowFlower.FetchCardsToHandAction;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EarthRelease extends AbstractRingCard {
    public static final String ID = RingOfDestiny.makeID("EarthRelease");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/ShadowFlower/19.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.ShadowFlower_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;


    public EarthRelease() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m){
        addToBot(new EarthReleaseAction(p));
        addToBot(new FetchCardsToHandAction(this.magicNumber, CardType.SKILL));
    }


    public AbstractCard makeCopy() {
        return new EarthRelease();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
        }
    }
}