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
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ProtectionOfLight extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("ProtectionOfLight");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/43.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/95.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public ProtectionOfLight(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);

    }

    @Override
    protected void initializeNumber1() {
        this.baseBlock = 9;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    protected void initializeNumber2() {
        this.baseBlock = 9;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public ProtectionOfLight() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new ProtectionOfLight();
        } else {
            return new ProtectionOfLight(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        upgradeBlock(3);
    }

    @Override
    protected void upgrade2() {
        upgradeBlock(3);
    }
}