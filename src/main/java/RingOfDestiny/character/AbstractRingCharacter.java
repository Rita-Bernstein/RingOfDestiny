package RingOfDestiny.character;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;

public abstract class AbstractRingCharacter extends CustomPlayer {
    public Texture shadow = ImageMaster.loadImage("RingOfDestiny/characters/shadow.png");
    public float shadowScale = 1.0f;

    public AbstractRingCharacter(String name, PlayerClass playerClass, EnergyOrbInterface energyOrbInterface, String model, String animation) {
        super(name, playerClass, energyOrbInterface, model, animation);
    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
    }

    @Override
    public void renderPlayerImage(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.setBlendFunction(770, 771);
        sb.draw(shadow, this.hb.cX - 86.0f, this.hb.y - 26.0F, 86.0F, 26.0F,
                172.0F, 53.0F,
                shadowScale * Settings.scale,
                shadowScale * Settings.scale, 0.0f,
                0, 0, 172, 53, false, false);
        super.renderPlayerImage(sb);
    }
}
