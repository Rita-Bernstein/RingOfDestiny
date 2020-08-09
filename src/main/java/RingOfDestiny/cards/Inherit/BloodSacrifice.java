package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.AddSubEnergyAction;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.CardColorEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BloodSacrifice extends AbstractRingCard {
    public static final String ID = RingOfDestiny.makeID("BloodSacrifice");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/28.png");
    private static final int COST = -2;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final CardType TYPE = CardType.STATUS;
    private static final CardColor COLOR = CardColorEnum.Inherit_LIME;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;


    public BloodSacrifice() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = 1;
        setBackgroundTexture("RingOfDestiny/img/cardui/Colorless/512/bg_skill_lime.png","RingOfDestiny/img/cardui/Colorless/1024/bg_skill_lime.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public AbstractCard makeCopy() {
        return new BloodSacrifice();
    }

    @Override
    public void triggerOnManualDiscard() {
        addToBot(new AddSubEnergyAction(this.magicNumber));
    }

    public void upgrade() {
    }
}