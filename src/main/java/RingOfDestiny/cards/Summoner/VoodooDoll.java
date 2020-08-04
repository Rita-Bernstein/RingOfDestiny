package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.UseSoulStoneAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.powers.Summoner.VoodooDollMonsterPower;
import RingOfDestiny.powers.Summoner.VoodooDollSelfPower;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class VoodooDoll extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("VoodooDoll");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/48.png");
    private static final int COST = -2;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;



    public VoodooDoll() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);

        this.tags.add(CustomTagsEnum.Soul_Stone);
        this.tags.add(CustomTagsEnum.SoleRare);

        this.isDestructive = true;
        this.exhaust = false;

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_rare.png", "RingOfDestiny/img/banner/1024/banner_rare.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_skill_rare.png", "RingOfDestiny/img/banner/1024/frame_skill_rare.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new UseSoulStoneAction(1));
       addToBot(new ApplyPowerAction(p,p,new VoodooDollSelfPower(p)));
       addToBot(new ApplyPowerAction(m,p,new VoodooDollMonsterPower(m,1),1));
    }

    public AbstractCard makeCopy() {
        return new VoodooDoll();
    }

    @SpireOverride
    protected void renderEnergy(SpriteBatch sb){
        soulStoneRenderHelper(sb, this.soulStoneOrbRenderColor, this.soulStoneOrb, this.current_x, this.current_y);
//        SpireSuper.call(sb);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isDestructive = false;
            this.exhaust = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

}
