package RingOfDestiny.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;


public abstract class AbstractRingCard extends CustomCard {

    public int secondaryM;
    public int baseSecondaryM;
    public boolean upgradesecondaryM;
    public boolean isSecondaryMModified;

    public AbstractRingCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color,CardRarity rarity, CardTarget target) {

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

    @Override
    public void displayUpgrades() {
        super.displayUpgrades();

        if(this.upgradesecondaryM){
            this.secondaryM = this.baseSecondaryM;
            this.isSecondaryMModified = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

    }



}