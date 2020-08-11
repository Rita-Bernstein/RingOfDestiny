package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.*;
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

public class PureHeart extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("PureHeart");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/47.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/100.png");
    private static final int COST = 1;
    private static final int SUB_GAIN = 0;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;

    public PureHeart(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);
        this.tags.add(CustomTagsEnum.SoleCard);
        this.tags.add(CustomTagsEnum.SoleUncommon);

        this.setBannerTexture("RingOfDestiny/img/banner/512/banner_uncommon.png","RingOfDestiny/img/banner/1024/banner_uncommon.png");
        this.setPortraitTextures("RingOfDestiny/img/banner/512/frame_power_uncommon.png","RingOfDestiny/img/banner/1024/frame_power_uncommon.png");
    }

    @Override
    protected void initializeNumber1() {
        this.cardsToPreview = new HolyJustice();
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    protected void initializeNumber2() {
        this.cardsToPreview = new HolyJustice(true);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public PureHeart() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            addToBot(new ApplyPowerAction(p,p,new UpgradedPureHeartPower(p,this.magicNumber),this.magicNumber));
        }else {
            addToBot(new ApplyPowerAction(p,p,new PureHeartPower(p,this.magicNumber),this.magicNumber));
        }
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
        if(upgraded){
            addToBot(new ApplyPowerAction(p,p,new UpgradedBlackCloakPower(p,this.magicNumber),this.magicNumber));
        }else {
            addToBot(new ApplyPowerAction(p,p,new BlackCloakPower(p,this.magicNumber),this.magicNumber));
        }
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new PureHeart();
        } else {
            return new PureHeart(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgradeNumber1() {
        this.cardsToPreview.upgrade();
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();

    }

    @Override
    protected void upgradeNumber2() {
        this.cardsToPreview.upgrade();
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    @Override
    public AbstractInheritCard getCardToPreview1() {
        AbstractInheritCard c = new HolyJustice();
        if(upgraded)c.upgrade();
        return c;
    }

    @Override
    public AbstractInheritCard getCardToPreview2() {
        AbstractInheritCard c = new HolyJustice(true);
        if(upgraded)c.upgrade();
        return c;
    }
}