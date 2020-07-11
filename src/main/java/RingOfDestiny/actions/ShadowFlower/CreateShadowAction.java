package RingOfDestiny.actions.ShadowFlower;

import RingOfDestiny.patches.ShadowPatches;
import RingOfDestiny.shadow.AbstractShadow;
import RingOfDestiny.shadow.LeftShadow;
import RingOfDestiny.shadow.NullShadow;
import RingOfDestiny.shadow.RightShadow;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;

public class CreateShadowAction extends AbstractGameAction {

    public CreateShadowAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            if (AbstractShadow.hasShadow(LeftShadow.SHADOW_ID)) {
                ShadowPatches.AbstractPlayerShadowFieldPatch.shadow.get(AbstractDungeon.player)[1].onExitShadow();
                ShadowPatches.AbstractPlayerShadowFieldPatch.shadow.get(AbstractDungeon.player)[1] = new RightShadow();
                ShadowPatches.AbstractPlayerShadowFieldPatch.shadow.get(AbstractDungeon.player)[1].onCreateShadow();
            } else {
                ShadowPatches.AbstractPlayerShadowFieldPatch.shadow.get(AbstractDungeon.player)[0].onExitShadow();
                ShadowPatches.AbstractPlayerShadowFieldPatch.shadow.get(AbstractDungeon.player)[0] = new LeftShadow();
                ShadowPatches.AbstractPlayerShadowFieldPatch.shadow.get(AbstractDungeon.player)[0].onCreateShadow();
            }


            if (Settings.FAST_MODE) {
                this.isDone = true;

                return;
            }
        }
        tickDuration();
    }
}


