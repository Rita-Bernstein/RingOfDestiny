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

public class HolyPressure extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("HolyPressure");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/39.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/91.png");
    private static final int COST = 2;
    private static final int SUB_GAIN = 2;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public HolyPressure(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);
        this.exhaust = true;
    }

    @Override
    protected void initializeNumber1() {
        this.isMultiDamage = true;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 9;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public HolyPressure() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyPowerAction(m,p,new WeakPower(m,this.magicNumber,false),this.magicNumber));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyPowerAction(m,p,new VulnerablePower(m,this.magicNumber,false),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new HolyPressure();
        } else {
            return new HolyPressure(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
       upgradeMagicNumber(1);

    }

    @Override
    protected void upgrade2() {
        upgradeMagicNumber(1);
    }
}