package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AddDiamondAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HundredDollar extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("HundredDollar");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/38.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;



    public HundredDollar() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 4;
        this.secondaryM = this.baseSecondaryM = 2;
        this.exhaust = true;

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_rare.png","RingOfDestiny/img/banner/1024/banner_uncommon.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_skill_rare.png","RingOfDestiny/img/banner/1024/frame_skill_rare.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddDiamondAction(this.magicNumber));
        if(upgraded){
            addToBot(new GainEnergyAction(2));
        }else {
            addToBot(new GainEnergyAction(1));
        }
        addToBot(new DrawCardAction(p,this.secondaryM));

    }

    public AbstractCard makeCopy() {
        return new HundredDollar();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
