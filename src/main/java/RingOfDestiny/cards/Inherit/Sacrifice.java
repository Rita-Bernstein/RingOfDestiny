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
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Sacrifice extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("Sacrifice");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/15.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/64.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Sacrifice(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);

    }

    @Override
    protected void initializeNumber1() {
        this.secondaryM = this.baseSecondaryM = 1;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    protected void initializeNumber2() {
        this.secondaryM = this.baseSecondaryM = 1;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public Sacrifice() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseMaxHPAction(p,p,this.secondaryM));
        addToBot(new ApplyPowerAction(m,p,new StrengthPower(p,this.magicNumber),this.magicNumber));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseMaxHPAction(p,p,this.secondaryM));
        addToBot(new ApplyPowerAction(m,p,new MetallicizePower(p,this.magicNumber),this.magicNumber));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new Sacrifice();
        } else {
            return new Sacrifice(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
       upgradeMagicNumber(1);

    }

    @Override
    protected void upgradeNumber2() {
        upgradeMagicNumber(2);
    }
}