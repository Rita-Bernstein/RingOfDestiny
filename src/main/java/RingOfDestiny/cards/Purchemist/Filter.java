package RingOfDestiny.cards.Purchemist;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractPurchemistCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Filter extends AbstractPurchemistCard {
	public static final String ID = RingOfDestiny.makeID("Filter");
	public static final String IMG = RingOfDestiny.assetPath("img/cards/Purchemist/57.png");
	private static final int COST = 0;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.SELF;

	public Filter() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = 1;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if(upgraded){
			addToBot(new ExhaustAction(this.magicNumber, false,true,false));
			addToBot(new DrawCardAction(p,this.magicNumber));
		}else {
			addToBot(new ExhaustAction(this.magicNumber, false));
			addToBot(new DrawCardAction(p,this.magicNumber));
		}

			}

	public AbstractCard makeCopy() {
		return new Filter();
	}

	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			upgradeMagicNumber(1);
			this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}