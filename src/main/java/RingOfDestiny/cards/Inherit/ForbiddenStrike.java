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

public class ForbiddenStrike extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("ForbiddenStrike");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/23.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/77.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 1;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public ForbiddenStrike(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    protected void initializeNumber1() {
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    protected void initializeNumber2() {

        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void autoGetSubCost(int cost) {
        this.subCost = 1;
    }

    public ForbiddenStrike() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseMaxHPAction(m,p,this.magicNumber));
        addToBot(new DiscardAction(p,p,1,false));
        addToBot(new DiscardToHandAction(this));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseMaxHPAction(m,p,this.magicNumber));
        addToBot(new ExhaustAction(p,p,1,false));
        addToBot(new DiscardToHandAction(this));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new ForbiddenStrike();
        } else {
            return new ForbiddenStrike(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        upgradeMagicNumber(1);

    }

    @Override
    protected void upgradeNumber2() {

        upgradeMagicNumber(1);
    }
}