package RingOfDestiny.cards.Summoner;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Summoner.ChainBladeAction;
import RingOfDestiny.cards.AbstractSummonerCard;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChainBlade extends AbstractSummonerCard {
    public static final String ID = RingOfDestiny.makeID("ChainBlade");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Summoner/61.png");
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public ChainBlade() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);

        this.baseDamage = 12;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChainBladeAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this));
        addToBot(new ModifyDamageAction(this.uuid, -this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new ChainBlade();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }
}