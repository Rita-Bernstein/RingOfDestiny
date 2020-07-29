package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractInheritCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike_IH extends AbstractInheritCard {
	public static final String ID = RingOfDestiny.makeID("Strike_IH");
	public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/01.png");
	private static final int COST = 1;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardRarity RARITY = CardRarity.BASIC;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public Strike_IH() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);

		this.baseDamage = 7;
		this.tags.add(CardTags.STARTER_STRIKE);
		this.tags.add(CardTags.STRIKE);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn),AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
			}

	public AbstractCard makeCopy() {
		return new Strike_IH();
	}

	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeDamage(3);
		}
	}
}