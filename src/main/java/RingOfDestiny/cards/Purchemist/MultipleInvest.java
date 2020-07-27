package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MultipleInvest extends AbstractPurchemistCard {
	public static final String ID = RingOfDestiny.makeID("MultipleInvest");
	public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/06.png");
	private static final int COST = 1;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public MultipleInvest() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = 8;
		this.magicNumber = this.baseMagicNumber = 1;
		this.secondaryM = this.baseSecondaryM = 3;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		addToBot(new DrawCardAction(p,this.magicNumber+ getDiamond()/this.secondaryM));
			}

	public AbstractCard makeCopy() {
		return new MultipleInvest();
	}

	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(2);
			upgradeMagicNumber(1);
		}
	}
}