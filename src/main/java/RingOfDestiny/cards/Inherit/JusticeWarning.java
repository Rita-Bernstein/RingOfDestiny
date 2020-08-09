package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

public class JusticeWarning extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("JusticeWarning");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/38.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/81.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public JusticeWarning(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);

    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 0;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 0;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public JusticeWarning() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal)));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal)));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void applyPowers() {
        if (isDark) {
            this.baseDamage = AbstractDungeon.player.exhaustPile.size() * this.magicNumber;
        } else {
            this.baseDamage = AbstractDungeon.player.discardPile.size() * this.magicNumber;
        }

        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }


    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new JusticeWarning();
        } else {
            return new JusticeWarning(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        upgradeMagicNumber(1);
        initializeDescription();

    }

    @Override
    protected void upgrade2() {
        upgradeMagicNumber(1);
    }
}