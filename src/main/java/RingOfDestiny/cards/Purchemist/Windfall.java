package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AddDiamondAction;
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

public class Windfall extends AbstractPurchemistCard {
	public static final String ID = RingOfDestiny.makeID("Windfall");
	public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/08.png");
	private static final int COST = 1;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	public Windfall() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = 2;
		this.secondaryM = this.baseSecondaryM = 3;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
 		addToBot(new AddDiamondAction(this.magicNumber));
			}

	@Override
	public void triggerOnManualDiscard() {
		addToBot(new AddDiamondAction(this.secondaryM));
	}

	public AbstractCard makeCopy() {
		return new Windfall();
	}

	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			upgradeSecondM(1);
		}
	}
}