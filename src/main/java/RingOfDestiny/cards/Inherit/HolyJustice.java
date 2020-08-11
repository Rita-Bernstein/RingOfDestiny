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

public class HolyJustice extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("HolyJustice");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/48.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/99.png");
    private static final int COST = 0;
    private static final int SUB_GAIN = 0;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public HolyJustice(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.isDestructive = true;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public HolyJustice() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (m.hasPower(ConvictionPower.POWER_ID)) {
            addToBot(new HealAction(p, p, this.magicNumber));
        }
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (m.hasPower(ConvictionPower.POWER_ID)) {
            addToBot(new LoseMaxHPAction(m, p, this.magicNumber));
        }
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new HolyJustice();
        } else {
            return new HolyJustice(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    protected void upgradeNumber2() {
        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 1;
    }
}