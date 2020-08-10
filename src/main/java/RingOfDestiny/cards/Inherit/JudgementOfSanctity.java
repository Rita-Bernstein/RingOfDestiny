package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.ChangeSubCostAction;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.SentencingAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.actions.MagicBullet.ChangeCostAction;
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

public class JudgementOfSanctity extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("JudgementOfSanctity");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/05.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/72.png");
    private static final int COST = 0;
    private static final int SUB_GAIN = 0;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public JudgementOfSanctity(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);

    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 13;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 7;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public JudgementOfSanctity() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new ChangeCostAction(this.uuid, this.magicNumber));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SentencingAction(this.magicNumber, m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        addToBot(new ChangeSubCostAction(this.uuid, this.magicNumber));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new JudgementOfSanctity();
        } else {
            return new JudgementOfSanctity(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
       upgradeDamage(5);

    }

    @Override
    protected void upgrade2() {
        upgradeDamage(3);
    }
}