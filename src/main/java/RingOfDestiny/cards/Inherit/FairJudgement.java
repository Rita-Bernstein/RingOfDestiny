package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.SentencingAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FairJudgement extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("FairJudgement");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/06.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/73.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public FairJudgement(boolean isDark) {

        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 9;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = false;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = true;
    }

    public FairJudgement() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyPowerAction(m, p, new ConvictionPower(m, this.magicNumber)));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SentencingAction(this.magicNumber, m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new FairJudgement();
        } else {
            return new FairJudgement(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        upgradeDamage(3);
        this.exhaust = false;
    }

    @Override
    protected void upgradeNumber2() {
        this.exhaust = true;
    }

    @Override
    protected void upgradeCost2() {
        upgradeBaseSubCost(0);
    }
}