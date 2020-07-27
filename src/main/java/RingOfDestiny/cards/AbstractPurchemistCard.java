package RingOfDestiny.cards;

import RingOfDestiny.patches.CardColorEnum;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;

import java.util.ArrayList;


public abstract class AbstractPurchemistCard extends AbstractRingCard {
    protected final CardStrings cardStrings;
    protected final String NAME;
    protected final String DESCRIPTION;
    protected final String UPGRADE_DESCRIPTION;
    protected final String[] EXTENDED_DESCRIPTION;

    public AbstractPurchemistCard(String id, String img, int cost,CardType type,  CardRarity rarity, CardTarget target) {
        super(id, CardCrawlGame.languagePack.getCardStrings(id).NAME, img, cost, CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION, type,
                CardColorEnum.Purchemist_LIME, rarity, target);

        cardStrings = CardCrawlGame.languagePack.getCardStrings(id);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        initializeTitle();
        initializeDescription();
    }


    public boolean hasEnoughDiamond(int amount) {
        return EnergyPanelRenderPatches.PatchEnergyPanelField.diamondManager.get(AbstractDungeon.overlayMenu.energyPanel).enoughDiamond(amount);
    }

    public int getDiamond() {
        return EnergyPanelRenderPatches.PatchEnergyPanelField.diamondManager.get(AbstractDungeon.overlayMenu.energyPanel).getCurrentDiamond();
    }
}