package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MaskOfSuffering extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("MaskOfSuffering");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/30.png");
    private static final int COST = 1;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;


    public MaskOfSuffering() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.misc = 4;
        this.baseBlock = this.misc;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;

        this.tags.add(CustomTagsEnum.SoleUncommon);

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_uncommon.png","RingOfDestiny/img/banner/1024/banner_uncommon.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_skill_uncommon.png","RingOfDestiny/img/banner/1024/frame_skill_uncommon.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IncreaseMiscAction(this.uuid, this.misc, this.magicNumber));
        addToBot(new GainBlockAction(p, p, this.block));
    }

    public void applyPowers() {
        this.baseBlock = this.misc;
        super.applyPowers();
        initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new MaskOfSuffering();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

}
