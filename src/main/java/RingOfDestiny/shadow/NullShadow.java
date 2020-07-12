package RingOfDestiny.shadow;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class NullShadow extends AbstractShadow {
    public static final String SHADOW_ID = "NullShadow";
    public static final float pos_x = 0.0f * Settings.scale;
    public static final float pos_y = 0.0f * Settings.scale;

    public NullShadow() {
        this.ID = SHADOW_ID;
    }

    @Override
    public void onPlayAttackCard(AbstractCard card, UseCardAction action) {
    }

    @Override
    public void onCreateShadow() {
    }

    @Override
    public void onExitShadow() {
    }

    @Override
    public void onEndOfTurn() {
    }

    public void updateAnimation() {
    }
}


