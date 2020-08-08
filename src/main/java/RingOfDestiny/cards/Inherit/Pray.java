package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.AddSubEnergyAction;
import RingOfDestiny.actions.Inherit.*;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Pray extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("Pray");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/24.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/105.png");
    private static final int COST = 0;
    private static final int SUB_GAIN = 0;
    private static final int SUB_GAIN2 = 6;
    public static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;


    public Pray(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public Pray() {
        this(false);
    }

    @Override
    protected void initializeNumber1() {
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    protected void initializeNumber2() {
        this.magicNumber = this.baseMagicNumber = 6;
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(3));
        addToBot(new HandChangeCostForTurnAction(0,this.magicNumber));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HandChangeSubCostForTurnAction(1,this.magicNumber));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new Pray();
        } else {
            return new Pray(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        upgradeMagicNumber(-1);
    }

    @Override
    protected void upgrade2() {
        upgradeMagicNumber(-2);
    }
}
