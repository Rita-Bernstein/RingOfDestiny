package RingOfDestiny.character;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;

import java.lang.reflect.Field;

public abstract class AbstractRingCharacter extends CustomPlayer {
    public Texture shadow = ImageMaster.loadImage("RingOfDestiny/characters/shadow.png");
    public float shadowScale = 1.0f;
    private static final Animation emptyAnimation = new Animation("<empty>", new Array(0), 0);
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

    public AnimationState.TrackEntry setEmptyAnimation (int trackIndex, float mixDuration) {
        AnimationState.TrackEntry entry = this.state.setAnimation(trackIndex, emptyAnimation, false);
        try {
            Field mixDurationField = AnimationState.TrackEntry.class.getDeclaredField("mixDuration");
            mixDurationField.setAccessible(true);
            mixDurationField.set(entry,mixDuration);
            Field endTimeField = AnimationState.TrackEntry.class.getDeclaredField("endTime");
            endTimeField.setAccessible(true);
            endTimeField.set(entry,mixDuration);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entry;
    }
}
