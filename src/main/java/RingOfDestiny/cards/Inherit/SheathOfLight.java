package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.EndlessPhagocytosisAction;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;

public class SheathOfLight extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("SheathOfLight");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/09.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/57.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public SheathOfLight(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.tags.add(CardTags.HEALING);
    }

    @Override
    protected void initializeNumber1() {
        this.baseDamage = 13;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = false;
    }

    @Override
    protected void initializeNumber2() {
        this.baseDamage = 9;
        this.magicNumber = this.baseMagicNumber = 4;
        this.exhaust = true;
    }

    public SheathOfLight() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (p.hasPower(StrengthPower.POWER_ID)) {
            if (p.getPower(StrengthPower.POWER_ID).amount > 0)
                addToBot(new HealAction(p, p, p.getPower(StrengthPower.POWER_ID).amount * this.magicNumber));
        }
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F));
        }

        addToBot(new EndlessPhagocytosisAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new SheathOfLight();
        } else {
            return new SheathOfLight(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        upgradeDamage(5);
        this.exhaust = false;
    }

    @Override
    protected void upgradeNumber2() {
        upgradeDamage(5);
        this.exhaust = true;
    }
}