package RingOfDestiny.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;


public abstract class AbstractRingCard extends CustomCard {

    public AbstractRingCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color,CardRarity rarity, CardTarget target) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);

    }


    public void updateDescription() {
        //Overwritten in cards
    }


    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

    }



}