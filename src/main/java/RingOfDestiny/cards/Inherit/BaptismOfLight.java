package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.BaptismOfLightAction;
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

public class BaptismOfLight extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("BaptismOfLight");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/22.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/69.png");
    private static final int COST = 5;
    private static final int SUB_GAIN = 0;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public BaptismOfLight(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.isDestructive = true;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    protected void initializeNumber1() {
        this.target = CardTarget.ENEMY;
        this.baseDamage = 30;

    }

    @Override
    protected void initializeNumber2() {
        this.target = CardTarget.ALL_ENEMY;
        this.magicNumber = this.baseMagicNumber = 35;
    }

    public BaptismOfLight() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new BaptismOfLightAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    addToBot(new LoseHPAction(monster, p, (int) Math.floor(monster.maxHealth * this.magicNumber * 0.01f)));
                }
            }
        }


        addToBot(new LoseHPAction(p, p, (int) Math.floor(p.maxHealth * this.magicNumber * 0.01f)));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new BaptismOfLight();
        } else {
            return new BaptismOfLight(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        this.target = CardTarget.ENEMY;
        upgradeDamage(10);
    }

    @Override
    protected void upgradeNumber2() {
        this.target = CardTarget.ALL_ENEMY;
        upgradeMagicNumber(15);
    }
}