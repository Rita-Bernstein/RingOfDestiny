package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.SentencingAction;
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

public class DawnJudgement extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("DawnJudgement");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/04.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/70.png");
    private static final int COST = 2;
    private static final int SUB_GAIN = 2;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public DawnJudgement(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.isMultiDamage = true;
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 18;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 7;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public DawnJudgement() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    addToBot(new ApplyPowerAction(monster, p, new ConvictionPower(monster, this.magicNumber), this.magicNumber));
                }
            }
        }
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            for (AbstractMonster monster : (AbstractDungeon.getMonsters()).monsters) {
                if (!monster.isDead && !monster.isDying) {
                    addToBot(new SentencingAction(this.magicNumber, m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
                }
            }
        }
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new DawnJudgement();
        } else {
            return new DawnJudgement(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        upgradeDamage(6);

    }

    @Override
    protected void upgradeNumber2() {

        upgradeDamage(2);
    }
}