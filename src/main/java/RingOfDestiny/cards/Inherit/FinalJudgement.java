package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.BlasphemyAction;
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

public class FinalJudgement extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("FinalJudgement");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/08.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/56.png");
    private static final int COST = 2;
    private static final int SUB_GAIN = 2;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public FinalJudgement(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);

    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 15;
        this.magicNumber = this.baseMagicNumber = 1;
        this.isAnotherDamage = true;
        this.secondaryM = this.baseSecondaryM = 0;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 15;
        this.magicNumber = this.baseMagicNumber = 1;
        this.isAnotherDamage = false;
    }

    public FinalJudgement() {
        this(false);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (!this.isDark) {
            if (mo.hasPower(ConvictionPower.POWER_ID)) {
                this.secondaryM = this.baseSecondaryM = mo.getPower(ConvictionPower.POWER_ID).amount * (this.baseDamage + this.magicNumber);
            }
        }
        super.calculateCardDamage(mo);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (!this.isDark) {
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
            initializeDescription();
        }

    }

    @Override
    public void onMoveToDiscard() {
        super.onMoveToDiscard();
        if (!this.isDark) {
            this.secondaryM = this.baseSecondaryM = 0;
            this.isSecondaryMModified = false;
            this.rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.secondaryM, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if (m.hasPower(ConvictionPower.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(m, p, ConvictionPower.POWER_ID));
        }
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BlasphemyAction(this.magicNumber, m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }


    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new FinalJudgement();
        } else {
            return new FinalJudgement(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        this.isAnotherDamage = true;
        upgradeMagicNumber(1);
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    protected void upgradeNumber2() {
        this.isAnotherDamage = false;
        upgradeMagicNumber(1);
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }
}