package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AddDiamondAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ThirtyDollar extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("ThirtyDollar");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/21.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;



    public ThirtyDollar() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.secondaryM = this.baseSecondaryM = 2;
        this.baseBlock = 0;
        this.cardsToPreview = new HundredDollar();
        this.isEthereal = true;
        this.purgeOnUse = true;
        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_uncommon.png","RingOfDestiny/img/banner/1024/banner_uncommon.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_skill_uncommon.png","RingOfDestiny/img/banner/1024/frame_skill_uncommon.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddDiamondAction(this.secondaryM));
        addToBot(new GainBlockAction(p,this.block));
        AbstractCard c = (new HundredDollar()).makeCopy();
        if(upgraded) c.upgrade();
        addToBot(new MakeTempCardInHandAction(c,1));
    }

    @Override
    public void applyPowers() {
        this.baseBlock = getDiamond() * 2;
        super.applyPowers();
        initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new ThirtyDollar();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeSecondM(1);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
