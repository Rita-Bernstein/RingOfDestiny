package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.GripOfMasterAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GripOfMaster extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("GripOfMaster");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/52.png");
    private static final int COST = -2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public GripOfMaster() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);

        this.baseDamage = 2;
        this.tags.add(CustomTagsEnum.Soul_Stone);
        this.tags.add(CustomTagsEnum.SoleRare);
        this.tags.add(CustomTagsEnum.SoleCard);
        this.exhaust = true;

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_rare.png","RingOfDestiny/img/banner/1024/banner_rare.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_attack_rare.png","RingOfDestiny/img/banner/1024/frame_attack_rare.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GripOfMasterAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public AbstractCard makeCopy() {
        return new GripOfMaster();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
        }
    }
}