package RingOfDestiny.cards.MagicBullet;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;
import RingOfDestiny.powers.LoomingDeathPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleTapPower;

public class LoomingDeath extends AbstractRingCard {
    public static final String ID = RingOfDestiny.makeID("LoomingDeath");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/MagicBullet/07.png");
    private static final int COST = 3;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.POWER;
    private static final CardColor COLOR = CardColorEnum.MagicBullet_LIME;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public LoomingDeath() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondaryM = this.baseSecondaryM = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LoomingDeathPower(p, this.secondaryM, this.magicNumber),this.secondaryM));
    }

    public AbstractCard makeCopy() {
        return new LoomingDeath();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(1);
        }
    }
}