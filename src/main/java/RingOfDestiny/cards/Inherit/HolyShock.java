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
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class HolyShock extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("HolyShock");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/19.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/75.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public HolyShock(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 0;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 0;
    }

    public HolyShock() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        getDamage();

        calculateCardDamage(m);
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {

        getDamage();
        calculateCardDamage(m);
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }


    @Override
    public void applyPowers() {
        getDamage();
        super.applyPowers();
    }


    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new HolyShock();
        } else {
            return new HolyShock(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
       upgradeBaseCost(0);

    }

    @Override
    protected void upgrade2() {
        upgradeBaseSubCost(0);
    }

    private void getDamage(){
        if(isDark){
            if(AbstractDungeon.player.hasPower(MetallicizePower.POWER_ID)){
                this.baseDamage = AbstractDungeon.player.getPower(MetallicizePower.POWER_ID).amount ;
            }else {
                this.baseDamage = 0;
            }
        }else {
            if(AbstractDungeon.player.hasPower(StrengthPower.POWER_ID)){
                this.baseDamage = AbstractDungeon.player.getPower(StrengthPower.POWER_ID).amount *2;
            }else {
                this.baseDamage = 0;
            }
        }
    }
}