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

public class HolyAssault extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("HolyAssault");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/32.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/85.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public HolyAssault(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 3;
        this.isEthereal = false;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 3;
        this.magicNumber = this.baseMagicNumber = 4;
        this.isEthereal = true;
    }

    public HolyAssault() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }

    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new HolyAssault();
        } else {
            return new HolyAssault(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        upgradeDamage(1);
        this.isEthereal = false;
    }

    @Override
    protected void upgrade2() {
        upgradeMagicNumber(1);
        this.isEthereal = true;
    }
}