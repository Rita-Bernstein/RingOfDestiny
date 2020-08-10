package RingOfDestiny.cards;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.CardColorEnum;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.patches.SummonPatches;
import RingOfDestiny.powers.AbstractRingPower;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;


public abstract class AbstractSummonerCard extends AbstractRingCard {
    protected final CardStrings cardStrings;
    protected final String NAME;
    protected final String DESCRIPTION;
    protected final String UPGRADE_DESCRIPTION;
    protected final String[] EXTENDED_DESCRIPTION;

    protected static final String SoulStoneCantUseMessage = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("SoulStoneCantUseMessage")).TEXT[0];
    protected static final String[] oriSoulStoneCantUseMessage = CardCrawlGame.languagePack.getUIString("SingleCardViewPopup").TEXT;
    protected static final Texture soulStoneOrb = ImageMaster.loadImage("RingOfDestiny/img/cardui/Summoner/512/card_lime_orb2.png");
    protected static final Color soulStoneOrbRenderColor = Color.WHITE.cpy();

    public AbstractSummonerCard(String id, String img, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, img, cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type,
                CardColorEnum.Summoner_LIME, rarity, target);

        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        initializeTitle();
        initializeDescription();
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (this.hasTag(CustomTagsEnum.Soul_Stone) && cardPlayable(m) && hasEnoughSoulStone(1)) {
            return true;
        } else {
            return super.canUse(p, m);
        }
    }


    @SpireOverride
    protected void renderEnergy(SpriteBatch sb) {
        if (this.hasTag(CustomTagsEnum.Soul_Stone)) {
            soulStoneRenderHelper(sb, soulStoneOrbRenderColor, soulStoneOrb, this.current_x, this.current_y);
        } else {
            SpireSuper.call(sb);
        }
    }

    protected int getCurrentSoulStone() {
        return EnergyPanelRenderPatches.PatchEnergyPanelField.soulStone.get(AbstractDungeon.overlayMenu.energyPanel).soulStoneAmount;
    }

    protected boolean hasEnoughSoulStone(int amount) {
        return hasEnoughSoulStone(amount, false);
    }

    protected boolean hasEnoughSoulStone(int amount, boolean soulStoneFreeToPlay) {
        if (AbstractDungeon.actionManager.turnHasEnded) {
            this.cantUseMessage = TEXT[9];
            return false;
        }

        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (!p.canPlayCard(this)) {
                this.cantUseMessage = oriSoulStoneCantUseMessage[13];
                return false;
            }
        }

        if (AbstractDungeon.player.hasPower("Entangled") && this.type == CardType.ATTACK) {
            this.cantUseMessage = oriSoulStoneCantUseMessage[10];
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

        if (getCurrentSoulStone() >= amount || freeToPlay() || this.isInAutoplay || soulStoneFreeToPlay) {
            return true;
        }

        this.cantUseMessage = SoulStoneCantUseMessage;
        return false;
    }


    protected int getCurrentSummonDamage() {
        return SummonPatches.AbstractPlayerSummonField.summon.get(AbstractDungeon.player).damage;
    }

    protected void soulStoneRenderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX - 256.0F, drawY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 512, 512, false, false);
    }
}