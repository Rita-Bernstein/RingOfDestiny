package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.powers.Summoner.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class EnvyNecklace extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("EnvyNecklace");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/39.png");
    private static final int COST = 2;
    public static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;



    public EnvyNecklace() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 2;
        this.tags.add(CustomTagsEnum.SoleUncommon);
        this.tags.add(CustomTagsEnum.SoleCard);
        this.cardsToPreview = new PowerOfEnvy();


        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_uncommon.png","RingOfDestiny/img/banner/1024/banner_uncommon.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_power_uncommon.png","RingOfDestiny/img/banner/1024/frame_power_uncommon.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            addToBot(new ApplyPowerAction(p, p, new UpgradeEnvyNecklacePower(p, this.magicNumber), this.magicNumber));
        } else {
            addToBot(new ApplyPowerAction(p, p, new EnvyNecklacePower(p, this.magicNumber), this.magicNumber));
        }
    }

    public AbstractCard makeCopy() {
        return new EnvyNecklace();
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
