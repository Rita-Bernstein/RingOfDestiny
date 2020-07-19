package RingOfDestiny.cards.MagicBullet;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;
import RingOfDestiny.patches.CustomTagsEnum;
import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class InitialBullet extends AbstractRingCard {
	public static final String ID = RingOfDestiny.makeID("InitialBullet");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String IMG = RingOfDestiny.assetPath("img/cards/MagicBullet/02.png");
	private static final int COST = 2;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final CardType TYPE = CardType.ATTACK;
	private static final CardColor COLOR = CardColorEnum.MagicBullet_LIME;
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;

	public InitialBullet(int upgrades) {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.baseDamage = 5;
		this.magicNumber = this.baseMagicNumber = 3;
		this.timesUpgraded = upgrades;
		this.tags.add(CustomTagsEnum.MagicBullet);
	}

	public InitialBullet() {
		this(0);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		int temp;
		for (temp = 0; temp < this.magicNumber; temp++) {
			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		}
			}

	public AbstractCard makeCopy() {
		return new InitialBullet(this.timesUpgraded);
	}

	public boolean canUpgrade() { return true; }

	public void upgrade() {
		this.upgradeMagicNumber(1);
		this.timesUpgraded++;
		this.upgraded = true;
		this.name = cardStrings.NAME + "+" + this.timesUpgraded;
		initializeTitle();

	}
}