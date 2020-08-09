package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class BathingInGrace extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("BathingInGrace");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/34.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/86.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public BathingInGrace(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);
        this.cardsToPreview = new BloodSacrifice();
    }

    @Override
    protected void initializeNumber1() {
        this.magicNumber = this.baseMagicNumber = 2;
        this.secondaryM = this.baseSecondaryM = 2;
        this.exhaust = false;
    }

    @Override
    protected void initializeNumber2() {
        this.magicNumber = this.baseMagicNumber = 3;
        this.secondaryM = this.baseSecondaryM = 3;
        this.exhaust = true;
    }

    public BathingInGrace() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p,this.magicNumber));
        addToBot(new MakeTempCardInHandAction(new BloodSacrifice(),this.secondaryM));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p,this.magicNumber));
        addToBot(new MakeTempCardInHandAction(new BloodSacrifice(),this.secondaryM));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new BathingInGrace();
        } else {
            return new BathingInGrace(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
       upgradeMagicNumber(1);
        this.exhaust = false;
    }

    @Override
    protected void upgrade2() {
        this.exhaust = true;
        upgradeMagicNumber(1);
    }
}