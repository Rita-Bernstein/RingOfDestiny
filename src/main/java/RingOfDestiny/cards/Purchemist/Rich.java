package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.RichAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Rich extends AbstractPurchemistCard {
	public static final String ID = RingOfDestiny.makeID("Rich");
	public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/34.png");
	private static final int COST = 1;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public Rich() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = 7;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new RichAction(p, m, this.damage, this.damageTypeForTurn, this.freeToPlayOnce));
			}

	public AbstractCard makeCopy() {
		return new Rich();
	}

	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(3);
		}
	}
}