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
import com.megacrit.cardcrawl.powers.*;

public class HolyGift extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("HolyGift");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/11.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/60.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 2;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public HolyGift(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
    }

    @Override
    protected void initializeNumber1() {
        this.target = CardTarget.ALL_ENEMY;
        this.magicNumber = this.baseMagicNumber = 3;
        this.exhaust = false;
    }

    @Override
    protected void initializeNumber2() {
        this.target = CardTarget.ENEMY;
        this.exhaust = true;
    }

    public HolyGift() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    addToBot(new ApplyPowerAction(monster, p, new StrengthPower(monster, this.magicNumber), this.magicNumber));
                }
            }
        }

        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));

    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(StrengthPower.POWER_ID)) {
            int amount = m.getPower(StrengthPower.POWER_ID).amount;
            addToBot(new ApplyPowerAction(p, p, new MetallicizePower(p, amount), amount));
            addToBot(new RemoveSpecificPowerAction(m, p, StrengthPower.POWER_ID));
        }
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new HolyGift();
        } else {
            return new HolyGift(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        this.target = CardTarget.ALL_ENEMY;
        this.subGain += 1;
        upgradeMagicNumber(1);
        this.exhaust = false;
    }

    @Override
    protected void upgradeNumber2() {
        this.target = CardTarget.ENEMY;
        this.exhaust = true;
    }
}