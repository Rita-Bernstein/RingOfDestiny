package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class CatGash extends AbstractPurchemistCard {
    public static final String ID = RingOfDestiny.makeID("CatGash");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/53.png");
    private static final int COST = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public CatGash() {
        super(ID, IMG, COST, TYPE, RARITY, TARGET);
        this.baseDamage = 1;
        this.magicNumber = this.baseMagicNumber = 4;
        this.secondaryM = this.baseSecondaryM = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
			addToBot(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,this.secondaryM,false),this.secondaryM));
    }

    public AbstractCard makeCopy() {
        return new CatGash();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(1);
            upgradeSecondM(1);
        }
    }
}