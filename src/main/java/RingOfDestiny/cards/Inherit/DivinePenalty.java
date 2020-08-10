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

public class DivinePenalty extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("DivinePenalty");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/21.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/59.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public DivinePenalty(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 35;
        this.magicNumber = this.baseMagicNumber = 10;
    }

    @Override
    protected void initializeNumber2() {
        this.secondaryM = this.baseSecondaryM = 3;
        this.magicNumber = this.baseMagicNumber = 30;
    }

    public DivinePenalty() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p, p, this.magicNumber));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseMaxHPAction(p, p, this.secondaryM));
        int amount = (int) Math.floor(this.magicNumber * m.maxHealth * 0.01f);
        if (m.type == AbstractMonster.EnemyType.BOSS)
            amount = (int) Math.floor(amount * 0.5f);

        addToBot(new LoseHPAction(m, p, amount));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new DivinePenalty();
        } else {
            return new DivinePenalty(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        upgradeDamage(15);
        upgradeMagicNumber(5);

    }

    @Override
    protected void upgrade2() {
        upgradeSecondM(1);
        upgradeMagicNumber(5);
    }
}