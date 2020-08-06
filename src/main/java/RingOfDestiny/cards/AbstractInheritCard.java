package RingOfDestiny.cards;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.AddSubEnergyAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.helpers.SubEnergyModifier;
import RingOfDestiny.patches.CardColorEnum;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import java.util.ArrayList;


public abstract class AbstractInheritCard extends AbstractRingCard {
    protected CardStrings cardStrings;
    protected String NAME;
    protected String DESCRIPTION;
    protected String UPGRADE_DESCRIPTION;
    protected String[] EXTENDED_DESCRIPTION;

    protected String rawID;
    protected String imgPath;
    protected String imgSubPath;

    protected static final String subEnergyCantUseMessage = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("SubEnergy")).EXTRA_TEXT[2];
    protected static final String[] oriCantUseMessage = CardCrawlGame.languagePack.getUIString("SingleCardViewPopup").TEXT;

    public boolean isDark = false;
    public int subCost;
    private int subGain;

    public AbstractInheritCard(String id, String img, int cost, CardType type, CardRarity rarity, CardTarget target, String imgSubPath, boolean currentFormIsDark, int subGain) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, img, cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type,
                CardColorEnum.Inherit_LIME, rarity, target);

        this.rawID = id;
        this.imgPath = img;
        this.imgSubPath = imgSubPath;
        this.subGain = subGain;
        initializeForm(currentFormIsDark);
        initializeTitle();
        initializeDescription();
        autoGetSubCost(cost);
        CardModifierManager.addModifier(this, new SubEnergyModifier());
    }

    public AbstractInheritCard(String id, String img, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this(id, img, cost, type, rarity, target, "RingOfDestiny/img/cards/Inherit/01.png", false, 0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isDark) {
            cardEffect2(p, m);
        } else {
            addToBot(new AddSubEnergyAction(this.subGain));
            cardEffect1(p, m);
        }
    }

    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
    }

    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
    }


    public void initializeForm(boolean isDark) {
        this.isDark = isDark;
        reloadCardIMG(isDark);
        reinitializeDescription();
    }

    public void reloadCardIMG(boolean isDark){
        if(isDark){
            setOrbTexture("RingOfDestiny/img/cardui/Inherit/512/card_lime_orb2.png","RingOfDestiny/img/cardui/Inherit/1024/card_lime_orb2.png");
            loadCardImage(imgSubPath);
        }else {
            setOrbTexture("RingOfDestiny/img/cardui/Inherit/512/card_lime_orb.png","RingOfDestiny/img/cardui/Inherit/1024/card_lime_orb.png");
            loadCardImage(imgPath);
        }

    }

    public void reinitializeDescription() {
        String finalSring = this.rawID;
        if (isDark) {
            finalSring = finalSring + 2;
        }

        cardStrings = CardCrawlGame.languagePack.getCardStrings(finalSring);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

        this.name = NAME;
        if (upgraded){
            upgradeName();
            this.rawDescription = getUpgradeDescription();
        }else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }

        initializeTitle();
        initializeDescription();
    }

    protected String getUpgradeDescription(){
        return DESCRIPTION;
    }

    public int getSubCost() {
        return EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).totalCount;
    }

    public void autoGetSubCost(int cost) {
        this.subCost = cost * 2;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (isDark && hasEnoughSubEnergy(this.subCost)) {
            return true;
        } else {
            return super.canUse(p, m);
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
    }


    public boolean hasEnoughSubEnergy(int amount) {
        return hasEnoughSubEnergy(amount, false);
    }

    public boolean hasEnoughSubEnergy(int amount, boolean subEnergyFreeToPlay) {
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.cantUseMessage = TEXT[9];
            return false;
        }

        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (!p.canPlayCard(this)) {
                this.cantUseMessage = oriCantUseMessage[13];
                return false;
            }
        }

        if (AbstractDungeon.player.hasPower("Entangled") && this.type == CardType.ATTACK) {
            this.cantUseMessage = oriCantUseMessage[10];
            return false;
        }

        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (!r.canPlay(this)) {
                return false;
            }
        }

        for (AbstractBlight b : AbstractDungeon.player.blights) {
            if (!b.canPlay(this)) {
                return false;
            }
        }

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (!c.canPlay(this)) {
                return false;
            }
        }

        if (getSubCost() >= amount || this.isInAutoplay || subEnergyFreeToPlay) {
            return true;
        }

        this.cantUseMessage = subEnergyCantUseMessage;
        return false;
    }


}