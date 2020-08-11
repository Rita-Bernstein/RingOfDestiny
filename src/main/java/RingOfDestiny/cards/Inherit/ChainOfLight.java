package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.BladeOfShadowPower;
import RingOfDestiny.powers.Inherit.ChainOfLightPower;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import RingOfDestiny.powers.Inherit.UpgradedChainOfLightPower;
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

public class ChainOfLight extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("ChainOfLight");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/25.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/78.png");
    private static final int COST = 0;
    private static final int SUB_GAIN = 0;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public ChainOfLight(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.tags.add(CardTags.HEALING);
    }

    @Override
    protected void initializeNumber1() {
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    protected void initializeNumber2() {
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public ChainOfLight() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            addToBot(new ApplyPowerAction(p, p, new UpgradedChainOfLightPower(p, this.magicNumber), this.magicNumber));
        } else {
            addToBot(new ApplyPowerAction(p, p, new ChainOfLightPower(p, this.magicNumber), this.magicNumber));
        }
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {

        addToBot(new ApplyPowerAction(p, p, new BladeOfShadowPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new ChainOfLight();
        } else {
            return new ChainOfLight(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    protected void upgradeNumber2() {
        upgradeMagicNumber(1);
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}