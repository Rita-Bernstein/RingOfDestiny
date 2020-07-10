package RingOfDestiny.cards.ShadowFlower;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

public class ForbiddenArts extends AbstractRingCard {
	public static final String ID = RingOfDestiny.makeID("ForbiddenArts");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String IMG = RingOfDestiny.assetPath("img/cards/ShadowFlower/55.png");
	private static final int COST = 0;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final CardType TYPE = CardType.SKILL;
	private static final CardColor COLOR = CardColorEnum.ShadowFlower_LIME;
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;


	public ForbiddenArts() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = 9;
		this.secondaryM = this.baseSecondaryM = 5;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		     if (Settings.FAST_MODE) {
			       addToBot(new VFXAction(new OfferingEffect(), 0.1F));
			     } else {
			       addToBot(new VFXAction(new OfferingEffect(), 0.5F));
			     }
		     addToBot(new LoseHPAction(p, p, this.magicNumber));
		     addToBot(new GainEnergyAction(3));
		     addToBot(new DrawCardAction(p, this.secondaryM));
			}

	public AbstractCard makeCopy() {
		return new ForbiddenArts();
	}

	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(-3);
		}
	}
}