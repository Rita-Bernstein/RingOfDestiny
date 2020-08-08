package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HonourJudgement extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("HonourJudgement");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/07.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/74.png");
    private static final int COST = 0;
    private static final int SUB_GAIN = 0;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public HonourJudgement(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    public HonourJudgement() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyPowerAction(m, p, new ConvictionPower(m, this.magicNumber)));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        int count = this.magicNumber;

        if (m.hasPower(ConvictionPower.POWER_ID)) {
            if (m.getPower(ConvictionPower.POWER_ID).amount >= 2) {
                count += 2;
                addToBot(new ReducePowerAction(m, p, ConvictionPower.POWER_ID, 2));
            } else {
                count += m.getPower(ConvictionPower.POWER_ID).amount;
                addToBot(new RemoveSpecificPowerAction(m, p, ConvictionPower.POWER_ID));
            }
        }

        for (int i = 0; i < count; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new HonourJudgement();
        } else {
            return new HonourJudgement(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        upgradeDamage(4);
        this.exhaust = true;
    }

    @Override
    protected void upgrade2() {
        upgradeDamage(1);
        this.exhaust = true;
    }
}