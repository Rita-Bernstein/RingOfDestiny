package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.DemonicAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Demonic extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("Demonic");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/14.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;



    public Demonic() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;

        this.tags.add(CustomTagsEnum.SoleUncommon);

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_uncommon.png","RingOfDestiny/img/banner/1024/banner_uncommon.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_skill_uncommon.png","RingOfDestiny/img/banner/1024/frame_skill_uncommon.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       if(upgraded){
           addToBot(new DemonicAction(this.magicNumber,CardType.SKILL));
       }else {
           addToBot(new DemonicAction(this.magicNumber,CardType.ATTACK));
       }

    }

    public AbstractCard makeCopy() {
        return new Demonic();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
