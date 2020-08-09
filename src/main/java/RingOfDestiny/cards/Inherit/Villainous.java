package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.SwitchFormAction;
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

public class Villainous extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("Villainous");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/52.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/103.png");
    private static final int COST = 0;
    private static final int SUB_GAIN = 2;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Villainous(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);
    }

    @Override
    protected void initializeNumber1() {
    }

    @Override
    protected void initializeNumber2() {
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public Villainous() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SwitchFormAction(true,true));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SwitchFormAction(true,false));
        addToBot(new GainEnergyAction(this.magicNumber));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new Villainous();
        } else {
            return new Villainous(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        this.subGain = 4;
    }

    @Override
    protected void upgrade2() {
        upgradeMagicNumber(1);
    }
}