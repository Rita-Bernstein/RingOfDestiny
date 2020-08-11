package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.DoubleMetallicizeAction;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;

import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.LimitBreakAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class PowerOfLight extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("PowerOfLight");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/13.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/62.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public PowerOfLight(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.isDestructive = true;
    }

    @Override
    protected void initializeNumber1() {
    }

    @Override
    protected void initializeNumber2() {
    }

    public PowerOfLight() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LimitBreakAction());
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoubleMetallicizeAction());
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new PowerOfLight();
        } else {
            return new PowerOfLight(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }


    @Override
    protected void upgradeCost1() {
        upgradeBaseCost(0);
    }

    @Override
    protected void upgradeCost2() {
        upgradeBaseSubCost(0);
    }
}