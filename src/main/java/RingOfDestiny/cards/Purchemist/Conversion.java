package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.TempIncreaseMaxHPAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Conversion extends AbstractPurchemistCard {
	public static final String ID = RingOfDestiny.makeID("Conversion");
	public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/42.png");
	private static final int COST = 1;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public Conversion() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = 6;
		this.magicNumber = this.baseMagicNumber = 4;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		addToBot(new TempIncreaseMaxHPAction(this.magicNumber,p));
			}

	public AbstractCard makeCopy() {
		return new Conversion();
	}

	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(4);
			upgradeMagicNumber(1);
		}
	}
}