package RingOfDestiny.actions.Summoner;

import RingOfDestiny.cards.Summoner.DoomsdayMeteorite;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DoomsdayRitualAction extends AbstractGameAction {
    private float startingDuration;
    private boolean upgraded;

    public DoomsdayRitualAction(boolean upgraded) {
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.upgraded = upgraded;
    }


    public void update() {
        int count = AbstractDungeon.player.hand.size();

        AbstractCard c = new DoomsdayMeteorite();
        if(this.upgraded) c.upgrade();
        addToBot(new MakeTempCardInHandAction(c, count));

        for (int i = 0; i < count; i++) {
            if (Settings.FAST_MODE) {
                addToTop(new ExhaustAction(1, true, true, false, Settings.ACTION_DUR_XFAST));
            } else {
                addToTop(new ExhaustAction(1, true, true));
            }
        }

        this.isDone = true;
    }
}


