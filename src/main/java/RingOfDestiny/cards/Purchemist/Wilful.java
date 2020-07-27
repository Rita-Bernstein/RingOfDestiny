package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Purchemist.AddDiamondAction;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Wilful extends AbstractPurchemistCard {
	public static final String ID = RingOfDestiny.makeID("Wilful");
	public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/09.png");
	private static final int COST = 1;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	public Wilful() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseBlock = 3;
		this.magicNumber = this.baseMagicNumber = 1;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
 		addToBot(new GainBlockAction(p,this.block));
		addToBot(new BetterDiscardPileToHandAction(this.magicNumber));
			}


	public AbstractCard makeCopy() {
		return new Wilful();
	}

	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			upgradeBlock(3);
		}
	}
}