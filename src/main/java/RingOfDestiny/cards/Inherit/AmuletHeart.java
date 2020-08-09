package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import RingOfDestiny.powers.Inherit.EmbersPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FeelNoPainPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class AmuletHeart extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("AmuletHeart");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/45.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/97.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 0;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public AmuletHeart(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);
        this.tags.add(CustomTagsEnum.SoleCard);
        this.tags.add(CustomTagsEnum.SoleUncommon);

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_uncommon.png","RingOfDestiny/img/banner/1024/banner_uncommon.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_power_uncommon.png","RingOfDestiny/img/banner/1024/frame_power_uncommon.png");
    }

    @Override
    protected void initializeNumber1() {
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    protected void initializeNumber2() {
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public AmuletHeart() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FeelNoPainPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EmbersPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new AmuletHeart();
        } else {
            return new AmuletHeart(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
       upgradeMagicNumber(1);

    }

    @Override
    protected void upgrade2() {
        upgradeMagicNumber(1);
    }
}