package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TortureBlade extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("TortureBlade");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/09.png");
    private static final int COST = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public TortureBlade() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public AbstractCard makeCopy() {
        return new TortureBlade();
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
            this.upgradeDamage(6);
        }
    }
}