package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.EvilBurnsAction;
import RingOfDestiny.actions.Inherit.HolyPouringAction;
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
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class HolyPouring extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("HolyPouring");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/10.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/58.png");
    private static final int COST = 2;
    private static final int SUB_GAIN = 2;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public HolyPouring(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.isMultiDamage = true;
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 8;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 10;
    }

    public HolyPouring() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HolyPouringAction(p, this.multiDamage, this.damageTypeForTurn));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EvilBurnsAction(p, this.multiDamage, this.damageTypeForTurn));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new HolyPouring();
        } else {
            return new HolyPouring(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        upgradeDamage(2);

    }

    @Override
    protected void upgradeNumber2() {
        upgradeDamage(2);
    }
}