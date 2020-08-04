package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.MagicBullet.ChangeCostAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import RingOfDestiny.patches.CustomTagsEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PowerOfWrath extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("PowerOfWrath");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/37.png");
    private static final int COST = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public PowerOfWrath() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);

        this.baseDamage = 3;
        this.magicNumber = this.baseMagicNumber = 1;

        this.tags.add(CustomTagsEnum.SoleRare);
        this.tags.add(CustomTagsEnum.Original_Sin);

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_rare.png","RingOfDestiny/img/banner/1024/banner_rare.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_attack_rare.png","RingOfDestiny/img/banner/1024/frame_attack_rare.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0;i < this.magicNumber;i++){
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        if(this.magicNumber < 4){
            upgradeMagicNumber(1);
            addToBot(new ChangeCostAction(this.uuid, 1));
        }

    }

    public AbstractCard makeCopy() {
        return new PowerOfWrath();
    }

    @Override
    public void update() {
        super.update();
        if(this.cost > 0){
            if(this.freeToPlayOnce){
                this.cost = this.costForTurn = 0;
            }
            this.cost = this.costForTurn;
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(1);
        }
    }
}