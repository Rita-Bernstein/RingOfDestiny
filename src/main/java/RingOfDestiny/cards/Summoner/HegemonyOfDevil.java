package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.MetallicizeToStrengthAction;
import RingOfDestiny.actions.Summoner.StrengthToMetallicizeAction;
import RingOfDestiny.actions.Summoner.UseSoulStoneAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.powers.Summoner.HegemonyOfDevilPower;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class HegemonyOfDevil extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("HegemonyOfDevil");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/49.png");
    private static final int COST = -2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;


    public HegemonyOfDevil() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 0;
        this.tags.add(CustomTagsEnum.Soul_Stone);
        this.tags.add(CustomTagsEnum.SoleRare);
        this.tags.add(CustomTagsEnum.SoleCard);
        this.exhaust = true;

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_rare.png", "RingOfDestiny/img/banner/1024/banner_rare.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_skill_rare.png", "RingOfDestiny/img/banner/1024/frame_skill_rare.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new UseSoulStoneAction(1));
        if(upgraded)
            addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,this.magicNumber),this.magicNumber));

        addToBot(new MetallicizeToStrengthAction());

        addToBot(new ApplyPowerAction(p,p,new HegemonyOfDevilPower(p)));
    }

    public AbstractCard makeCopy() {
        return new HegemonyOfDevil();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
