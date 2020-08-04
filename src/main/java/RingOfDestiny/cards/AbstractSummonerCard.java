package RingOfDestiny.cards;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.patches.CardColorEnum;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import RingOfDestiny.patches.SummonPatches;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.evacipated.cardcrawl.modthespire.lib.SpireSuper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;


public abstract class AbstractSummonerCard extends AbstractRingCard {
    protected final CardStrings cardStrings;
    protected final String NAME;
    protected final String DESCRIPTION;
    protected final String UPGRADE_DESCRIPTION;
    protected final String[] EXTENDED_DESCRIPTION;

    protected static final String SoulStoneCantUseMessage = CardCrawlGame.languagePack.getUIString(RingOfDestiny.makeID("SoulStoneCantUseMessage")).TEXT[0];
    protected static final Texture soulStoneOrb = ImageMaster.loadImage("RingOfDestiny/img/cardui/Summoner/512/card_lime_orb2.png");
    protected static final Color soulStoneOrbRenderColor = Color.WHITE.cpy();

    public AbstractSummonerCard(String id, String img, int cost,CardType type,  CardRarity rarity, CardTarget target) {
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
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }

        if (!hasEnoughSoulStone(1) && this.hasTag(CustomTagsEnum.Soul_Stone)) {
            canUse = false;
            this.cantUseMessage = SoulStoneCantUseMessage;
        }

        return canUse;
    }

    @SpireOverride
    protected void renderEnergy(SpriteBatch sb){
        if(this.hasTag(CustomTagsEnum.Soul_Stone)){
            soulStoneRenderHelper(sb, soulStoneOrbRenderColor, soulStoneOrb, this.current_x, this.current_y);
        }else {
            SpireSuper.call(sb);
        }
    }

    protected int getCurrentSoulStone(){
        return EnergyPanelRenderPatches.PatchEnergyPanelField.soulStone.get(AbstractDungeon.overlayMenu.energyPanel).soulStoneAmount;
    }

    protected boolean hasEnoughSoulStone(int amount){
        boolean enough = false;
        if(amount <= getCurrentSoulStone()){
            enough = true;
        }

        return enough;
    }


    protected int getCurrentSummonDamage(){
        return SummonPatches.AbstractPlayerSummonField.summon.get(AbstractDungeon.player).damage;
    }

    protected void soulStoneRenderHelper(SpriteBatch sb, Color color, Texture img, float drawX, float drawY) {
        sb.setColor(color);
        sb.draw(img, drawX - 256.0F, drawY - 256.0F, 256.0F, 256.0F, 512.0F, 512.0F, this.drawScale * Settings.scale, this.drawScale * Settings.scale, this.angle, 0, 0, 512, 512, false, false);
    }
}