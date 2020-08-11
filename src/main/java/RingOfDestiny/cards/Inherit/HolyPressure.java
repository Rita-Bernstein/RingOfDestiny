package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.cards.Colorless.*;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
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

public class HolyPressure extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("HolyPressure");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/39.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/91.png");
    private static final int COST = 2;
    private static final int SUB_GAIN = 2;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public HolyPressure(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.exhaust = true;
        this.cardsToPreview = new BloodSacrifice();
    }

    @Override
    protected void initializeNumber1() {
        this.isMultiDamage = true;
        this.baseDamage = 14;

    }

    @Override
    protected void initializeNumber2() {
        this.isMultiDamage = false;
        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 4;
    }

    public HolyPressure() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        addToBot(new MakeTempCardInHandAction(new BloodSacrifice(), 2));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }

        addToBot(new MakeTempCardInHandAction(new BloodSacrifice(), 2));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new HolyPressure();
        } else {
            return new HolyPressure(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        this.isMultiDamage = true;
        upgradeDamage(4);

    }

    @Override
    protected void upgradeNumber2() {
        this.isMultiDamage = false;
        upgradeDamage(1);
    }
}