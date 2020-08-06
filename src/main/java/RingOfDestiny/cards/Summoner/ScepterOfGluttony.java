package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.powers.Summoner.ScepterOfGluttonyPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ScepterOfGluttony extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("ScepterOfGluttony");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/40.png");
    private static final int COST = 3;
    public static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;


    public ScepterOfGluttony() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = 6;
        this.magicNumber = this.baseMagicNumber = 1;
        this.tags.add(CustomTagsEnum.SoleRare);
        this.tags.add(CustomTagsEnum.SoleCard);

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_rare.png","RingOfDestiny/img/banner/1024/banner_rare.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_power_rare.png","RingOfDestiny/img/banner/1024/frame_power_rare.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new ScepterOfGluttonyPower(p,this.magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new ScepterOfGluttony();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }

}
