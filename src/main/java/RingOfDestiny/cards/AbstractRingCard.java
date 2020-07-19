package RingOfDestiny.cards;

import RingOfDestiny.patches.CustomTagsEnum;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;


public abstract class AbstractRingCard extends CustomCard {

    public int secondaryM;
    public int baseSecondaryM;
    public boolean upgradesecondaryM;
    public boolean isSecondaryMModified;

    public AbstractRingCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);

    }


    public void updateDescription() {
        //Overwritten in cards
    }

    protected void upgradeSecondM(int amount) {
        this.baseSecondaryM += amount;
        this.secondaryM = this.baseSecondaryM;
        this.upgradesecondaryM = true;
    }

    protected AbstractCard returnNonSoleCommonCard() {
        ArrayList<AbstractCard> returnCard = new ArrayList<AbstractCard>();
        for (AbstractCard c : AbstractDungeon.srcCommonCardPool.group) {
            if (!c.hasTag(CustomTagsEnum.SoleCard)) {
                returnCard.add(c);
            }
        }
        return returnCard.get(AbstractDungeon.miscRng.random(returnCard.size() - 1));
    }

    protected AbstractCard returnNonSoleUncommonCard() {
        ArrayList<AbstractCard> returnCard = new ArrayList<AbstractCard>();
        for (AbstractCard c : AbstractDungeon.srcUncommonCardPool.group) {
            if (!c.hasTag(CustomTagsEnum.SoleCard)) {
                returnCard.add(c);
            }
        }
        return returnCard.get(AbstractDungeon.miscRng.random(returnCard.size() - 1));
    }

    protected AbstractCard returnNonSoleRareCard() {
        ArrayList<AbstractCard> returnCard = new ArrayList<AbstractCard>();
        for (AbstractCard c : AbstractDungeon.srcRareCardPool.group) {
            if (!c.hasTag(CustomTagsEnum.SoleCard)) {
                returnCard.add(c);
            }
        }
        return returnCard.get(AbstractDungeon.miscRng.random(returnCard.size() - 1));
    }

    @Override
    public void displayUpgrades() {
        super.displayUpgrades();

        if (this.upgradesecondaryM) {
            this.secondaryM = this.baseSecondaryM;
            this.isSecondaryMModified = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

    }


}