package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.Colorless.*;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.cards.AbstractRingCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Offering;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ForbiddenBook extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("ForbiddenBook");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/27.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/80.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;


    public ForbiddenBook(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);

    }

    @Override
    protected void initializeNumber1() {
        this.cardsToPreview = new ForbiddenStrike();
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    protected void initializeNumber2() {
        this.cardsToPreview = new ForbiddenStrike(true);
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public ForbiddenBook() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        AbstractCard s = (new ForbiddenStrike()).makeCopy();
        if (upgraded) s.upgrade();
        addToBot(new MakeTempCardInHandAction(s, this.magicNumber));
        addToBot(new MakeTempCardInHandAction(new BloodSacrifice(),2));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        AbstractCard s = (new ForbiddenStrike(true)).makeCopy();
        if (upgraded) s.upgrade();
        addToBot(new MakeTempCardInHandAction(s, this.magicNumber));
        addToBot(new MakeTempCardInHandAction(new BloodSacrifice(),2));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new ForbiddenBook();
        } else {
            return new ForbiddenBook(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        this.cardsToPreview.upgrade();
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();

    }

    @Override
    protected void upgradeNumber2() {
        this.cardsToPreview.upgrade();
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    public AbstractInheritCard getCardToPreview1() {
        AbstractInheritCard c = new ForbiddenStrike();
        if(upgraded)c.upgrade();
        return c;
    }

    @Override
    public AbstractInheritCard getCardToPreview2() {
        AbstractInheritCard c = new ForbiddenStrike(true);
        if(upgraded)c.upgrade();
        return c;
    }
}