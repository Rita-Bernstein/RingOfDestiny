package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import RingOfDestiny.powers.Inherit.ResurrectionGemPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ResurrectionGem extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("ResurrectionGem");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/18.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/68.png");
    private static final int COST = 3;
    private static final int SUB_GAIN = 0;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;

    public ResurrectionGem(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN, SUB_GAIN2);
        this.subCost = 4;
        this.isDestructive = true;
        this.tags.add(CustomTagsEnum.SoleRare);
        this.tags.add(CustomTagsEnum.SoleCard);

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_rare.png", "RingOfDestiny/img/banner/1024/banner_rare.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_power_rare.png", "RingOfDestiny/img/banner/1024/frame_power_rare.png");
    }

    @Override
    protected void initializeNumber1() {
        this.magicNumber = this.baseMagicNumber = 50;
    }

    @Override
    protected void initializeNumber2() {
        this.magicNumber = this.baseMagicNumber = 1;
        this.secondaryM = this.baseSecondaryM = 3;
    }

    public ResurrectionGem() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new ResurrectionGemPower(p,p,this.magicNumber),this.magicNumber));
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseMaxHPAction(p, p, this.secondaryM));
        addToBot(new ApplyPowerAction(p, p, new BerserkPower(p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new ResurrectionGem();
        } else {
            return new ResurrectionGem(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        upgradeMagicNumber(50);
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    protected void upgrade2() {
        upgradeMagicNumber(1);
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}