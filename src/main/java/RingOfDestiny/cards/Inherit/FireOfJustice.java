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

public class FireOfJustice extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("FireOfJustice");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/36.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/89.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int trueBaseDamage = 8;
    private static final int upgradedTrueBaseDamage = 10;

    public FireOfJustice(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 4;
        this.secondaryM = this.baseSecondaryM = 2;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 4;
        this.secondaryM = this.baseSecondaryM = 2;
    }

    public FireOfJustice() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new FireOfJustice();
        } else {
            return new FireOfJustice(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int cardsAmount = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
        if(upgraded){
            this.baseDamage = trueBaseDamage + (cardsAmount / this.magicNumber) * this.secondaryM;
        }else {
            this.baseDamage = upgradedTrueBaseDamage + (cardsAmount / this.magicNumber) * this.secondaryM;
        }

        initializeDescription();
    }

    @Override
    protected void upgradeNumber1() {
        upgradeDamage(2);
       upgradeMagicNumber(-2);

    }

    @Override
    protected void upgradeNumber2() {
        upgradeDamage(2);
        upgradeMagicNumber(-2);
    }
}