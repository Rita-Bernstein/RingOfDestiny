package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.*;
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

public class HymnOfAngels extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("HymnOfAngels");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/49.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/101.png");
    private static final int COST = 2;
    private static final int SUB_GAIN = 2;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public HymnOfAngels(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
    }

    @Override
    protected void initializeNumber1() {
    }

    @Override
    protected void initializeNumber2() {
    }

    public HymnOfAngels() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HymnOfAngelsPower(p, 1), 1));
        addToBot(new ApplyPowerAction(p, p, new CantPlayNonSkillPower(p, 1), 1));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DevilWhispersPower(p, 1), 1));
        addToBot(new ApplyPowerAction(p, p, new CantPlayNonAttackPower(p, 1), 1));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new HymnOfAngels();
        } else {
            return new HymnOfAngels(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        upgradeBaseCost(1);

    }

    @Override
    protected void upgrade2() {
        upgradeBaseSubCost(2);
    }
}