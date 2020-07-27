package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class StayUpLate extends AbstractPurchemistCard {
	public static final String ID = RingOfDestiny.makeID("StayUpLate");
	public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/07.png");
	private static final int COST = 1;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public StayUpLate() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = 7;
		this.magicNumber = this.baseMagicNumber = 1;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		addToBot(new ApplyPowerAction(m,p,new WeakPower(m,this.magicNumber,false),this.magicNumber));
			}

	public AbstractCard makeCopy() {
		return new StayUpLate();
	}

	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(3);
			upgradeMagicNumber(1);
		}
	}
}