package RingOfDestiny.actions.MagicBullet;

import RingOfDestiny.cards.MagicBullet.OriginBullet;
import RingOfDestiny.patches.CustomTagsEnum;
import RingOfDestiny.powers.EtchPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class RiteOfOriginAction extends AbstractGameAction {
    private boolean upgraded;
    private int cardsToMake;

    public RiteOfOriginAction(boolean upgraded) {
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
    }

    public void update() {
        this.cardsToMake = 0;
        ArrayList<AbstractCard> handCopy = new ArrayList();
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(CustomTagsEnum.MagicBullet)) {
                handCopy.add(c);
            }
        }

        ArrayList<AbstractCard> deckCopy = new ArrayList();
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c.hasTag(CustomTagsEnum.MagicBullet)) {
                deckCopy.add(c);
            }
        }

        this.cardsToMake = handCopy.size() + deckCopy.size();

        for (AbstractCard c : handCopy) {
            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }
        handCopy.clear();

        for (AbstractCard c : deckCopy) {
            AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.drawPile));
        }
        deckCopy.clear();


        AbstractCard s = (new OriginBullet()).makeCopy();
        if(this.upgraded) s.upgrade();
        addToBot(new MakeTempCardInHandAction(s, this.cardsToMake));

        isDone = true;
    }
}


