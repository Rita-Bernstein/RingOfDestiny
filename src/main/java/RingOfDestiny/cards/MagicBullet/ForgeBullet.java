package RingOfDestiny.cards.MagicBullet;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;
import RingOfDestiny.patches.CustomTagsEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ForgeBullet extends AbstractRingCard {
    public static final String ID = RingOfDestiny.makeID("ForgeBullet");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/MagicBullet/55.png");
    private static final int COST = 1;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.MagicBullet_LIME;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;



    public ForgeBullet() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
        this.tags.add(CustomTagsEnum.MagicBullet);
        this.cardsToPreview = new OriginBullet();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard s = (new OriginBullet()).makeCopy();
        if(upgraded) s.upgrade();
        addToBot(new MakeTempCardInHandAction(s, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new ForgeBullet();
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
