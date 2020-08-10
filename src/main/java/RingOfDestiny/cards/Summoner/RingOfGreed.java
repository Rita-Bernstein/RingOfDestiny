package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.powers.LoseHpPower;
import RingOfDestiny.powers.Summoner.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;

public class RingOfGreed extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("RingOfGreed");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/38.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;


    public RingOfGreed() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 5;
        this.secondaryM = this.baseSecondaryM = 2;
        this.tags.add(CustomTagsEnum.SoleUncommon);
        this.tags.add(CustomTagsEnum.SoleCard);

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_uncommon.png", "RingOfDestiny/img/banner/1024/banner_uncommon.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_power_uncommon.png", "RingOfDestiny/img/banner/1024/frame_power_uncommon.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new LoseHpPower(p, this.secondaryM), this.secondaryM));
    }

    public AbstractCard makeCopy() {
        return new RingOfGreed();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeSecondM(-1);
            upgradeBaseCost(0);
        }
    }

}
