package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.DoubleConvictionAction;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import RingOfDestiny.powers.Inherit.ExpiateSinPower;
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

public class ExpiateSin extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("ExpiateSin");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/51.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/65.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public ExpiateSin(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.isDestructive = true;
    }

    @Override
    protected void initializeNumber1() {
        this.target = CardTarget.SELF;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    protected void initializeNumber2() {
        this.target = CardTarget.ENEMY;
    }

    public ExpiateSin() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ExpiateSinPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        if (!this.upgraded) {
            addToBot(new DoubleConvictionAction(m));
        } else {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot(new DoubleConvictionAction(mo));
            }
        }
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new ExpiateSin();
        } else {
            return new ExpiateSin(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        this.target = CardTarget.SELF;
        upgradeMagicNumber(1);
    }

    @Override
    protected void upgrade2() {
        this.target = CardTarget.ALL_ENEMY;
        upgradeMagicNumber(1);
    }
}