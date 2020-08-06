package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.UseSoulStoneAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ModifyBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PowerOfLaziness extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("PowerOfLaziness");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/46.png");
    private static final int COST = -2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;


    public PowerOfLaziness() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseBlock = 15;
        this.magicNumber = this.baseMagicNumber = 5;
        this.secondaryM = this.baseSecondaryM = 25;
        this.tags.add(CustomTagsEnum.Original_Sin);
        this.tags.add(CustomTagsEnum.SoleRare);
        this.tags.add(CustomTagsEnum.SoleCard);
        this.tags.add(CustomTagsEnum.Soul_Stone);

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_rare.png", "RingOfDestiny/img/banner/1024/banner_rare.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_skill_rare.png", "RingOfDestiny/img/banner/1024/frame_skill_rare.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new UseSoulStoneAction(1));
        addToBot(new GainBlockAction(p, p, this.block));
        if (this.baseBlock < this.secondaryM) {
            addToBot(new ModifyBlockAction(this.uuid, this.magicNumber));
        }
    }


    public AbstractCard makeCopy() {
        return new PowerOfLaziness();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeSecondM(5);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
