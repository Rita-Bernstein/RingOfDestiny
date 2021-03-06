package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Strike_IH extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("Strike_IH");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/01.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/54.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Strike_IH(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);

        this.baseDamage = 7;
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(CardTags.STRIKE);
    }

    public Strike_IH() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new Strike_IH();
        } else {
            return new Strike_IH(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
    }
}