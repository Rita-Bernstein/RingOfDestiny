package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.SummonAttackDamageAction;
import RingOfDestiny.actions.Summoner.UseSoulStoneAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.evacipated.cardcrawl.modthespire.lib.SpireSuper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PowerOfDesire extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("PowerOfDesire");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/47.png");
    private static final int COST = -2;
    public static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;


    public PowerOfDesire() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.secondaryM = this.baseSecondaryM = 2;
        this.magicNumber = this.baseMagicNumber = 3;
        this.baseDamage = 4;

        this.tags.add(CardTags.HEALING);
        this.tags.add(CustomTagsEnum.Original_Sin);
        this.tags.add(CustomTagsEnum.Soul_Stone);

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_uncommon.png", "RingOfDestiny/img/banner/1024/banner_uncommon.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_attack_uncommon.png", "RingOfDestiny/img/banner/1024/frame_attack_uncommon.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new UseSoulStoneAction(1));
        addToBot(new HealAction(p, p, this.secondaryM));
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }


    @SpireOverride
    protected void renderEnergy(SpriteBatch sb){
        soulStoneRenderHelper(sb, this.soulStoneOrbRenderColor, this.soulStoneOrb, this.current_x, this.current_y);
//        SpireSuper.call(sb);
    }

    public AbstractCard makeCopy() {
        return new PowerOfDesire();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeSecondM(1);
            upgradeDamage(1);

        }
    }

}
