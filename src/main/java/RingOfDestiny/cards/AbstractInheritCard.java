package RingOfDestiny.cards;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.AddSubEnergyAction;

import RingOfDestiny.helpers.SubEnergyModifier;
import RingOfDestiny.patches.CardColorEnum;

import RingOfDestiny.patches.EnergyPanelRenderPatches;

import RingOfDestiny.powers.AbstractRingPower;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.evacipated.cardcrawl.modthespire.lib.SpireSuper;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public abstract class AbstractInheritCard extends AbstractRingCard {
    protected CardStrings cardStrings;
    protected String NAME;
    protected String DESCRIPTION;
    protected String UPGRADE_DESCRIPTION;
    protected String[] EXTENDED_DESCRIPTION;

    public String rawID;
    public String imgPath;
    public String imgSubPath;
    public Texture imgLarge;
    public Texture imgSubLarge;

    private static final Color ENERGY_COST_RESTRICTED_COLOR = new Color(1.0F, 0.3F, 0.3F, 1.0F);
    private static final Color ENERGY_COST_MODIFIED_COLOR = new Color(0.4F, 1.0F, 0.4F, 1.0F);
    protected static final String subEnergyCantUseMessage = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("SubEnergy")).EXTRA_TEXT[2];
    protected static final String[] oriCantUseMessage = CardCrawlGame.languagePack.getUIString("SingleCardViewPopup").TEXT;

    public boolean isDark = false;
    public int subGain;
    public int subGain2;

    public int subCost;
    public int subCostForTurn;
    public boolean isSubCostModified;
    public boolean isSubCostModifiedForTurn;
    public boolean upgradedSubCost;

    public int subEnergyOnUse = 0;
    public boolean ignoreSubEnergyOnUse = false;

    public boolean flipOrb = false;
    private static final Texture oriOrb = ImageMaster.loadImage("RingOfDestiny/img/cardui/Inherit/512/card_lime_orb.png");
    private static final Texture subOrb = ImageMaster.loadImage("RingOfDestiny/img/cardui/Inherit/512/card_lime_orb2.png");
    public static final Texture subGainOrb = ImageMaster.loadImage("RingOfDestiny/img/ui/topPanel/Inherit/tsundere.png");

    protected static final float subGainOrbScale = 1.8f;
    protected static final Color drakOrbRenderColor = Color.WHITE.cpy();

    public AbstractInheritCard(String id, String img, int cost, CardType type, CardRarity rarity, CardTarget target, String imgSubPath, boolean currentFormIsDark, int subGain, int subGain2) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, img, cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type,
                CardColorEnum.Inherit_LIME, rarity, target);

        this.rawID = id;

        this.imgPath = img;
        this.imgSubPath = imgSubPath;

        this.subGain = subGain;
        this.subGain2 = subGain2;
        initializeNumber();
        initializeForm(currentFormIsDark);
        initializeTitle();
        initializeDescription();
        initializeSubCost(cost);
        CardModifierManager.addModifier(this, new SubEnergyModifier());
    }

    public AbstractInheritCard(String id, String img, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this(id, img, cost, type, rarity, target, "RingOfDestiny/img/cards/Inherit/01.png", false, 0, 0);
    }

    public Texture getLargeCardIMG(String img) {
        int endingIndex = img.lastIndexOf(".");
        return ImageMaster.loadImage(img.substring(0, endingIndex) + "_p" + img.substring(endingIndex));
    }

    protected void initializeNumber() {
        if (isDark) {
            initializeNumber2();
            if (upgraded)
                this.upgradeNumber2();
        } else {
            initializeNumber1();
            if (upgraded)
                this.upgradeNumber1();
        }
    }

    protected void initializeNumber1() {

    }

    protected void initializeNumber2() {

    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isDark) {
            addToBot(new AddSubEnergyAction(this.subGain2));
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

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            if (isDark) {
                upgradeNumber2();
                upgradeCost2();
            } else {
                upgradeNumber1();
                upgradeCost1();
            }
        }
    }

    protected void upgradeNumber1() {
    }

    protected void upgradeCost1() {
    }

    protected void upgradeNumber2() {
    }

    protected void upgradeCost2() {
    }

    public void initializeForm(boolean isDark) {
        this.isDark = isDark;
        initializeNumber();
        reloadCardIMG(isDark);
        reinitializeDescription();
        reGetCardToPreview();
        if (upgraded) {
            this.upgrade();
        }
    }

    public void reloadCardIMG(boolean isDark) {
        if (isDark) {
            loadCardImage(imgSubPath);
        } else {
            loadCardImage(imgPath);
        }

    }

    public void reGetCardToPreview() {
        AbstractInheritCard cardForPreview;
        if (isDark) {
            cardForPreview = getCardToPreview2();
        } else {
            cardForPreview = getCardToPreview1();
        }
        if (cardForPreview != null)
            this.cardsToPreview = cardForPreview;
    }

    public void reinitializeDescription() {
        this.cardID = this.rawID;
        if (isDark) {
            this.cardID = this.cardID + 2;
        }

        cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

        this.name = NAME;
        if (upgraded) {
            upgradeName();
            this.rawDescription = getUpgradeDescription();
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }

        initializeTitle();
        initializeDescription();
    }

    protected String getUpgradeDescription() {
        return UPGRADE_DESCRIPTION;
    }

    public int getSubTotalCount() {
        return EnergyPanelRenderPatches.PatchEnergyPanelField.subEnergy.get(AbstractDungeon.overlayMenu.energyPanel).totalCount;
    }

    public void initializeSubCost(int cost) {
        isSubCostModified = false;
        isSubCostModifiedForTurn = false;
        upgradedSubCost = false;
        autoGetSubCost(cost);
        subCostForTurn = subCost;
    }

    public void reinitializeSubCost(int cost) {
        subCost = subCostForTurn = cost;
    }

    public void autoGetSubCost(int cost) {
        if (cost > 0) {
            this.subCost = cost * 2;
        } else {
            this.subCost = cost;
        }

    }


    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
    }


    public boolean hasEnoughSubEnergy(int amount) {
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.cantUseMessage = oriCantUseMessage[9];
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

        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof AbstractRingPower) {
                AbstractRingPower power = (AbstractRingPower) p;

                if (!power.canPlayCard(this)) {
                    this.cantUseMessage = power.getCantPlayMessage();
                    SpireReturn.Return(false);
                }
            }
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

        if (getSubTotalCount() >= subCostForTurn || this.isInAutoplay || freeToPlay()) {
            return true;
        }

        this.cantUseMessage = subEnergyCantUseMessage;
        return false;
    }


    @Override
    public void displayUpgrades() {
        super.displayUpgrades();

        if (upgradedSubCost) {
            isSubCostModified = true;
        }
    }


    protected void upgradeBaseSubCost(int newBaseCost) {
        super.upgradeBaseCost(newBaseCost);
        int diff = this.subCostForTurn - this.subCost;
        this.subCost = newBaseCost;

        if (this.subCostForTurn > 0) {
            this.subCostForTurn = this.subCost + diff;
        }
        if (this.subCostForTurn < 0) {
            this.subCostForTurn = 0;
        }
        this.upgradedSubCost = true;
    }


    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractInheritCard card = (AbstractInheritCard) super.makeStatEquivalentCopy();
        card.isDark = this.isDark;

        this.cardID = this.rawID;
        if (isDark) {
            this.cardID = this.cardID + 2;
        }
        card.cardID = this.cardID;

        card.cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

        this.name = NAME;
        if (upgraded) {
            upgradeName();
            card.rawDescription = getUpgradeDescription();
        } else {
            card.rawDescription = cardStrings.DESCRIPTION;
        }

        card.initializeTitle();
        card.initializeDescription();

        card.cardsToPreview = this.cardsToPreview;

        card.subCost = this.subCost;
        card.subCostForTurn = this.subCostForTurn;
        card.isSubCostModified = this.isSubCostModified;
        card.isSubCostModifiedForTurn = this.isSubCostModifiedForTurn;
        card.secondaryM = this.secondaryM;
        card.baseSecondaryM = this.baseSecondaryM;
        card.upgradesecondaryM = this.upgradesecondaryM;
        card.isSecondaryMModified = this.isSecondaryMModified;
        card.isSingleAndAOE = this.isSingleAndAOE;
        card.isAnotherDamage = this.isAnotherDamage;
        card.isDestructive = this.isDestructive;
        return card;
    }

    public AbstractInheritCard getCardToPreview1() {
        return null;
    }

    public AbstractInheritCard getCardToPreview2() {
        return null;
    }


    public void updateSubCost(int amt) {
        int tmpCost = this.subCost;
        int diff = this.subCost - this.subCostForTurn;

        tmpCost += amt;
        if (tmpCost < 0) {
            tmpCost = 0;
        }

        if (tmpCost != this.cost) {
            this.isSubCostModified = true;
            this.subCost = tmpCost;
            this.subCostForTurn = this.cost - diff;

            if (this.subCostForTurn < 0) {
                this.subCostForTurn = 0;
            }
        }
    }


    public void setSubCostForTurn(int amt) {
        if (this.subCostForTurn >= 0) {
            this.subCostForTurn = amt;
            if (this.subCostForTurn < 0) {
                this.subCostForTurn = 0;
            }

            if (this.subCostForTurn != this.subCost) {
                this.isSubCostModifiedForTurn = true;
            }
        }
    }


    public void modifySubCostForCombat(int amt) {
        super.modifyCostForCombat(amt);
        if (this.subCostForTurn > 0) {
            this.subCostForTurn += amt;
            if (this.subCostForTurn < 0) {
                this.subCostForTurn = 0;
            }

            if (this.subCost != this.subCostForTurn) {
                this.isSubCostModified = true;
            }
            this.subCost = this.subCostForTurn;
        } else if (this.subCost >= 0) {
            this.subCost += amt;
            if (this.subCost < 0) {
                this.subCost = 0;
            }
            this.subCostForTurn = 0;
            if (this.subCost != this.subCostForTurn) {
                this.isSubCostModified = true;
            }
        }
    }


    @SpireOverride
    protected void renderEnergy(SpriteBatch sb) {
        if (this.isDark) {
            if (this.subCost <= -2 || this.isLocked || !this.isSeen) {
                return;
            }

            if (this.flipOrb) {
                darkOrbRenderHelper(sb, drakOrbRenderColor, oriOrb, this.current_x, this.current_y);
            } else {
                darkOrbRenderHelper(sb, drakOrbRenderColor, subOrb, this.current_x, this.current_y);
            }


            Color costColor = Color.WHITE.cpy();
            if (AbstractDungeon.player != null && AbstractDungeon.player.hand.contains(this) && !hasEnoughSubEnergy(this.subCostForTurn)) {
                costColor = ENERGY_COST_RESTRICTED_COLOR;
            } else if (this.isSubCostModified || this.isSubCostModifiedForTurn || freeToPlay()) {
                costColor = ENERGY_COST_MODIFIED_COLOR;
            }
            costColor.a = this.transparency;

            String text = getSubCost();
            BitmapFont font = getEnergyFont();

            if ((this.type != CardType.STATUS || this.cardID.equals("Slimed")) && (this.color != CardColor.CURSE || this.cardID.equals("Pride"))) {
                FontHelper.renderRotatedText(sb, font, text, this.current_x, this.current_y, -132.0F * this.drawScale * Settings.scale, 192.0F * this.drawScale * Settings.scale, this.angle, false, costColor);
            }


            for (int i = 1; i <= subGain2; i++) {
                subGainOrbRenderHelper(sb,
                        -75.0f,
                        95.0f - i * 20.0f
                );
            }

        } else {

            if (this.cost > -2 && !this.isLocked && this.isSeen) {

                if (this.flipOrb) {
                    darkOrbRenderHelper(sb, drakOrbRenderColor, subOrb, this.current_x, this.current_y);
                } else {
                    darkOrbRenderHelper(sb, drakOrbRenderColor, oriOrb, this.current_x, this.current_y);
                }


                Color costColor = Color.WHITE.cpy();
                if (AbstractDungeon.player != null && AbstractDungeon.player.hand.contains(this) && !this.hasEnoughEnergy()) {
                    costColor = ENERGY_COST_RESTRICTED_COLOR;
                } else if (this.isCostModified || this.isCostModifiedForTurn || this.freeToPlay()) {
                    costColor = ENERGY_COST_MODIFIED_COLOR;
                }

                costColor.a = this.transparency;
                String text = this.getCost();
                BitmapFont font = this.getEnergyFont();
                if ((this.type != AbstractCard.CardType.STATUS || this.cardID.equals("Slimed")) && (this.color != AbstractCard.CardColor.CURSE || this.cardID.equals("Pride"))) {
                    FontHelper.renderRotatedText(sb, font, text, this.current_x, this.current_y, -132.0F * this.drawScale * Settings.scale, 192.0F * this.drawScale * Settings.scale, this.angle, false, costColor);
                }

            }

            for (int i = 1; i <= subGain; i++) {
                subGainOrbRenderHelper(sb,
                        -75.0f,
                        95.0f - i * 20.0f
                );
            }
        }
    }


    protected void darkOrbRenderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX - 256.0F, drawY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 512, 512, false, false);
    }

    protected void subGainOrbRenderHelper(SpriteBatch sb, float posX, float posY) {
        sb.setColor(drakOrbRenderColor);
        float length = (float) Math.sqrt(posX * posX + posY * posY);
        float angleFinal = (float) Math.toRadians(this.angle + 180.0f - (float) Math.toDegrees(Math.sinh(posY / length)));
        float drawX = this.current_x + length * (float) Math.cos(angleFinal) * this.drawScale * Settings.scale * subGainOrbScale;
        float drawY = this.current_y + length * (float) Math.sin(angleFinal) * this.drawScale * Settings.scale * subGainOrbScale;


        sb.draw(subGainOrb, drawX - 7.0F, drawY - 7.0F, 7.0F, 7.0F, 14.0F, 14.0F, this.drawScale * Settings.scale * subGainOrbScale, this.drawScale * Settings.scale * subGainOrbScale, this.angle, 0, 0, 14, 14, false, false);
    }

    protected String getSubCost() {
        if (this.subCost == -1)
            return "X";
        if (freeToPlay()) {
            return "0";
        }
        return Integer.toString(this.subCostForTurn);
    }

    protected String getCost() {
        if (this.cost == -1)
            return "X";
        if (freeToPlay()) {
            return "0";
        }
        return Integer.toString(this.costForTurn);
    }

    private BitmapFont getEnergyFont() {
        FontHelper.cardEnergyFont_L.getData().setScale(this.drawScale);
        return FontHelper.cardEnergyFont_L;
    }

}