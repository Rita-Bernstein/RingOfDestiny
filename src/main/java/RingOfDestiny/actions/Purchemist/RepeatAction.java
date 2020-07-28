package RingOfDestiny.actions.Purchemist;

import RingOfDestiny.cards.Purchemist.Repeat;
import RingOfDestiny.diamonds.AbstractDiamond;
import RingOfDestiny.diamonds.DiamondManager;
import RingOfDestiny.patches.EnergyPanelRenderPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RepeatAction extends AbstractGameAction {

    public RepeatAction() {
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() > 1) {
                AbstractCard card = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size() - 2).makeSameInstanceOf();
                if (!card.cardID.equals(Repeat.ID)) {
                    card.purgeOnUse = true;
                    addToBot(new NewQueueCardAction(card, true, false, true));
                } else {
                    isDone = true;
                }
            }
        }
        tickDuration();
    }
}


