package RingOfDestiny.cards.Inherit;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.actions.Inherit.LoseMaxHPAction;
import RingOfDestiny.actions.Inherit.UseSubEnergyAction;
import RingOfDestiny.cards.AbstractInheritCard;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.powers.Inherit.ConvictionPower;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.evacipated.cardcrawl.modthespire.lib.SpireSuper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Resuscitation extends AbstractInheritCard {
    public static final String ID = RingOfDestiny.makeID("Resuscitation");
    public static final String IMG = RingOfDestiny.assetPath("img/cards/Inherit/31.png");
    public static final String SUB_IMG = RingOfDestiny.assetPath("img/cards/Inherit/84.png");
    private static final int COST = -1;
    private static final int SUB_GAIN = 0;
    private static final int SUB_GAIN2 = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final Color orbRenderColor = Color.WHITE.cpy();
    private static final Texture oriOrb = ImageMaster.loadImage("RingOfDestiny/img/cardui/Inherit/512/card_lime_orb.png");
    private static final Texture subOrb = ImageMaster.loadImage("RingOfDestiny/img/cardui/Inherit/512/card_lime_orb2.png");

    public Resuscitation(boolean isDark) {
        super(ID, IMG, COST, TYPE, RARITY, TARGET, SUB_IMG, isDark, SUB_GAIN,SUB_GAIN2);
        this.tags.add(CardTags.HEALING);
        this.flipOrb = true;
    }

    private void soulStoneRenderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX - 256.0F, drawY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 512, 512, false, false);
    }

    @SpireOverride
    protected void renderEnergy(SpriteBatch sb) {
        if (this.isDark) {
            soulStoneRenderHelper(sb, orbRenderColor, oriOrb, this.current_x, this.current_y);
        } else {
            soulStoneRenderHelper(sb, orbRenderColor, subOrb, this.current_x, this.current_y);
        }
    }

    @Override
    protected void initializeNumber1() {
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    protected void initializeNumber2() {
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public Resuscitation() {
        this(false);
    }

    @Override
    protected void cardEffect1(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    protected void cardEffect2(AbstractPlayer p, AbstractMonster m) {
    }

    public AbstractCard makeCopy() {
        if (AbstractDungeon.player == null) {
            return new Resuscitation();
        } else {
            return new Resuscitation(EnergyPanelRenderPatches.PatchEnergyPanelField.isInDarkCpy);
        }
    }

    @Override
    protected void upgrade1() {
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    @Override
    protected void upgrade2() {
        upgradeMagicNumber(1);
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}