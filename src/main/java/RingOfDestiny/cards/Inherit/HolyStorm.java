package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.ChaosStromAction;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.WhirlwindAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class HolyStorm extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("HolyStorm");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/33.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/104.png");
    private static final int COST = -1;
    private static final int SUB_GAIN = 3;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public HolyStorm(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.isMultiDamage = true;
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 5;
        this.isSingleAndAOE = false;

    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 9;
        this.magicNumber = this.baseMagicNumber = 0;
    }

    public HolyStorm() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new WhirlwindAction(p, this.multiDamage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ChaosStromAction(p, this.multiDamage, this.damageTypeForTurn, this.freeToPlayOnce, this.subEnergyOnUse));

        if (upgraded) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }

        initializeDescription();
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new HolyStorm();
        } else {
            return new HolyStorm(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    public void applyPowers() {
        this.baseDamage = this.subEnergyOnUse;
        if (AbstractDungeon.player.hasRelic("Chemical X")) {
            this.baseDamage += 2;
        }

        if (upgraded) this.baseDamage += this.magicNumber;
        super.applyPowers();

        if (upgraded) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }

        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);

        if (upgraded) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }

        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];

        initializeDescription();
    }

    @Override
    protected void upgrade1() {
        upgradeDamage(3);
        this.isSingleAndAOE = false;
        this.modifyAlterDamage = false;
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    protected void upgrade2() {
        upgradeMagicNumber(2);
        this.isSingleAndAOE = true;
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}