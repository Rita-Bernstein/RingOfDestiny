package RingOfDestiny.cards.MagicBullet;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.MagicBullet.DanceOfDeathAction;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DanceOfDeath extends AbstractRingCard {
    public static final String ID = RingOfDestiny.makeID("DanceOfDeath");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/MagicBullet/28.png");
    private static final int COST = 2;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = CardColorEnum.MagicBullet_LIME;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;



    public DanceOfDeath() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 3;
        this.secondaryM = this.baseSecondaryM = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (randomMonster != null) {

        }
        addToBot(new DanceOfDeathAction(randomMonster, this.secondaryM, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new DanceOfDeath();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

}
