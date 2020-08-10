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
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class BladeOfFaith extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("BladeOfFaith");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/20.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/76.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public BladeOfFaith(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.tags.add(CardTags.HEALING);
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 8;

    }

    public BladeOfFaith() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new HealAction(p, p, this.magicNumber));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (m.hasPower(StrengthPower.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(m, p, StrengthPower.POWER_ID));
        }
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new BladeOfFaith();
        } else {
            return new BladeOfFaith(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        upgradeDamage(2);
        upgradeMagicNumber(2);

    }

    @Override
    protected void upgrade2() {
        upgradeDamage(4);
    }
}