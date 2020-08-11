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
import com.megacrit.cardcrawl.powers.DrawReductionPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class SacredOverload extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("SacredOverload");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/14.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/63.png");
    private static final int COST = 0;
    private static final int SUB_GAIN = 0;
    private static final int SUB_GAIN2 = 4;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public SacredOverload(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);

    }

    @Override
    protected void initializeNumber1() {
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    protected void initializeNumber2() {
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public SacredOverload() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            addToBot(new GainEnergyAction(2));
        }else {
            addToBot(new GainEnergyAction(3));
        }

        addToBot(new ApplyPowerAction(p,p,new DrawReductionPower(p,this.magicNumber),this.magicNumber));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new DrawReductionPower(p,2),2));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new SacredOverload();
        } else {
            return new SacredOverload(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    protected void upgradeNumber2() {
        this.subGain2 += 2;
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}